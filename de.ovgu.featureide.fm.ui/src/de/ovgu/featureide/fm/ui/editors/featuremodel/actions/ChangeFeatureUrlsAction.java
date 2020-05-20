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

import static de.ovgu.featureide.fm.core.localization.StringTable.CHANGE_URLS;
import static de.ovgu.featureide.fm.core.localization.StringTable.FEATURE_URLS;
import static de.ovgu.featureide.fm.core.localization.StringTable.FEATURE_URL_FORMAT;
import static de.ovgu.featureide.fm.core.localization.StringTable.PLEASE_ENTER_URLS_FOR_FEATURE_;

import de.ovgu.featureide.fm.core.base.event.FeatureIDEEvent;
import de.ovgu.featureide.fm.core.base.event.FeatureIDEEvent.EventType;
import de.ovgu.featureide.fm.core.io.manager.IFeatureModelManager;
import de.ovgu.featureide.fm.ui.FMUIPlugin;
import de.ovgu.featureide.fm.ui.editors.ChangeFeatureUrlDialog;

/**
 * TODO description
 *
 * @author mariana
 */
public class ChangeFeatureUrlsAction extends SingleSelectionAction {

	public static final String ID = "de.ovgu.featureide.changefeatureurls";

	public ChangeFeatureUrlsAction(Object viewer, IFeatureModelManager featureModelManager, Object graphicalViewer) {
		super(CHANGE_URLS, viewer, ID, featureModelManager);
		setImageDescriptor(FMUIPlugin.getDefault().getImageDescriptor("icons/write_obj.gif"));
	}

	@Override
	public void run() {
		String urls = "";
		if (feature.getProperty().getUrls() != null) {
			urls = feature.getProperty().getUrls();
			urls = urls.trim();
		}
		final ChangeFeatureUrlDialog dialog =
			new ChangeFeatureUrlDialog(null, FEATURE_URLS, PLEASE_ENTER_URLS_FOR_FEATURE_ + feature.getName() + "'\n" + FEATURE_URL_FORMAT, urls);
		dialog.open();
		final String urlstemp = dialog.getValue();

		// TODO implement as operation
		if (!urls.equals(urlstemp.trim())) {
			feature.getProperty().setUrls(urlstemp);
			feature.getFeatureModel().fireEvent(new FeatureIDEEvent(feature, EventType.ATTRIBUTE_CHANGED));
		}
	}

	@Override
	protected void updateProperties() {
		setEnabled(true);
	}
}
