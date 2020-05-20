/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2017  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 *
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.fm.ui.editors;

import static de.ovgu.featureide.fm.core.localization.StringTable.FEATURE_URL_TOOL_TIP;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.ovgu.featureide.fm.ui.editors.featuremodel.GUIDefaults;

/**
 * TODO description
 *
 * @author mariana
 */
public class ChangeFeatureUrlDialog extends Dialog implements GUIDefaults {

	private final String title;

	private String message;

	private String value = "";

	private Button okButton;

	private Text text;

	private CLabel label;

	private boolean lostInternet;

	private final String initmessage;

	public ChangeFeatureUrlDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue) {
		super(parentShell);
		super.setShellStyle(SWT.TITLE);
		title = dialogTitle;
		message = dialogMessage;
		initmessage = message;
		lostInternet = false;
		if (initialValue == null) {
			value = "";
		} else {
			value = initialValue;
		}
	}

	protected boolean validate() {
		if (!hasInternet()) {
			label.setText(initmessage);
			label.setImage(null);
			okButton.setEnabled(true);
			noInternetPopUp();
			return true;
		}
		String firstError = "";
		int nErrors = 0;
		final String[] urls = value.split("\n");
		for (final String u : urls) {
			if (u.isBlank()) {
				continue;
			}
			final String[] splitUrl = u.split(" - ");
			String url = null;
			if (splitUrl.length == 1) {
				url = splitUrl[0];
			} else if (splitUrl.length == 2) {
				url = splitUrl[1];
			}
			if ((splitUrl.length > 2) || !checkUrl(url)) {
				if (nErrors == 0) {
					firstError = splitUrl[0];
				}
				nErrors++;

			}
		}
		if (lostInternet) {
			noInternetPopUp();
		}
		if (nErrors > 0) {
			message = "Error while validating:\n";
			if (nErrors == 1) {
				message += "There is 1 error\nIn ";
			} else {
				message += "There are " + nErrors + " errors.\nThe first error is in ";
			}
			message += "line '" + firstError + "'.\nPlease check all URLs are correct, in the right format, and try again.";
			label.setText(message);
			label.setImage(ERROR_IMAGE);
			okButton.setEnabled(false);
			return false;
		}
		label.setText(initmessage);
		label.setImage(null);
		okButton.setEnabled(true);
		return true;

	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			value = text.getText();
			if (!validate()) {
				return;
			}
		} else {
			value = null;
		}
		super.buttonPressed(buttonId);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (title != null) {
			shell.setText(title);
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		text.setFocus();
		if (value != null) {
			text.setText(value);
			text.selectAll();
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite composite = (Composite) super.createDialogArea(parent);
		if (message != null) {
			label = new CLabel(composite, SWT.WRAP);
			label.setText(message);
			label.setToolTipText(FEATURE_URL_TOOL_TIP);
			final GridData data =
				new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER);
			data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
			label.setLayoutData(data);
			label.setFont(parent.getFont());
		}
		text = new Text(composite, getInputTextStyle());
		text.setLayoutData(new GridData(500, 100));

		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				okButton.setEnabled(true);
			}
		});

		applyDialogFont(composite);
		return composite;
	}

	protected Button getOkButton() {
		return okButton;
	}

	protected Text getText() {
		return text;
	}

	protected int getInputTextStyle() {
		return SWT.MULTI | SWT.BORDER | SWT.V_SCROLL;
	}

	public String getValue() {
		if ((value == null) || value.equals("")) {
			return " ";
		}
		return value;
	}

	private boolean checkUrl(String u) {
		if (!hasInternet()) {
			lostInternet = true;
			return true;
		}
		URL url = null;
		try {
			url = new URL(u);
		} catch (final Exception e) {
			return false;
		}
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setInstanceFollowRedirects(true);
			int status = con.getResponseCode();
			while ((status == HttpURLConnection.HTTP_MOVED_TEMP) || (status == HttpURLConnection.HTTP_MOVED_PERM)) {// Checks redirecting
				final String location = con.getHeaderField("Location");
				final URL newUrl = new URL(location);
				con = (HttpURLConnection) newUrl.openConnection();
				status = con.getResponseCode();

			}
			if ((status >= HttpURLConnection.HTTP_OK) && (status <= HttpURLConnection.HTTP_PARTIAL)) {
				return true;
			}

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean hasInternet() {
		URL url;
		try {
			url = new URL("https://www.google.com/"); // reliable link
			final HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			final int status = con.getResponseCode();
			if ((status >= HttpURLConnection.HTTP_OK) && (status <= HttpURLConnection.HTTP_PARTIAL)) {
				return true;
			}

		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	private void noInternetPopUp() {
		String errorMessage = null, errorTitle = null;
		if (!lostInternet) {
			errorTitle = "No internet connection";
			errorMessage = "Unable to verify validity of URLs";
		} else {
			errorTitle = "Lost internet connection while validating URLs";
			errorMessage = "Unable to verify validity of all URLs, because the internet connection was lost";
		}
		JOptionPane.showMessageDialog(new JFrame(), errorMessage, errorTitle, JOptionPane.WARNING_MESSAGE);
	}

}
