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
package de.ovgu.featureide.fm.ui.editors.featuremodel.actions;

import java.awt.Desktop;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.ovgu.featureide.fm.core.io.manager.IFeatureModelManager;

/**
 * TODO description
 *
 * @author mariana
 */
public class OpenFeatureUrlAction extends SingleSelectionAction {

	public static final String ID = "de.ovgu.featureide.openfeatureurl";

	private final String urlLabel;
	private final String url;

	public OpenFeatureUrlAction(Object viewer, IFeatureModelManager featureModelManager, Object graphicalViewer, String urlLabel, String url) {
		super(urlLabel, viewer, ID, featureModelManager);
		this.urlLabel = urlLabel;
		this.url = url;

	}

	@Override
	public void run() {
		if (!hasInternet()) {
			errorPopUp(false);
		} else {
			openUrl();
		}
	}

	private void openUrl() {
		if (Desktop.isDesktopSupported()) {
			URI uri = null;
			try {
				uri = new URI(url);
				Desktop.getDesktop().browse(uri);
			} catch (final URISyntaxException | IOException e) {
				errorPopUp(true);
			}
		}
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

	private void errorPopUp(boolean hasInternet) {
		String errorMessage = null, errorTitle = null;
		if (!hasInternet) {
			errorTitle = "No internet connection";
			errorMessage = "Unable to establish internet connection.\nPlease check your connection and try again.";
		} else {
			errorTitle = "Invalid URL";
			errorMessage = "Unable to reach '" + urlLabel + "'. Please check URL is correct and try again";
		}
		JOptionPane.showMessageDialog(new JFrame(), errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}

	@Override
	protected void updateProperties() {
		setEnabled(true);
	}
}
