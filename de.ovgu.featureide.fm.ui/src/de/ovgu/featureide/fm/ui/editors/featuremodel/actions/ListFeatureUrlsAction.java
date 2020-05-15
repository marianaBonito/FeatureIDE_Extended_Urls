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

import static de.ovgu.featureide.fm.core.localization.StringTable.LIST_URLS;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;

import de.ovgu.featureide.fm.core.io.manager.IFeatureModelManager;
import de.ovgu.featureide.fm.ui.editors.FeatureDiagramViewer;

/**
 * TODO description
 *
 * @author mariana
 */
public class ListFeatureUrlsAction extends SingleSelectionAction {

	public static final String ID = "de.ovgu.featureide.listfeatureurls";

	private final MenuManager listUrls;
	private final MenuManager prevContextMenu;

	public ListFeatureUrlsAction(FeatureDiagramViewer viewer, IFeatureModelManager featureModelManager, MenuManager prevContextMenu) {
		super(LIST_URLS, viewer, ID, featureModelManager);
		listUrls = new MenuManager();
		setEnabled(true);
		setChecked(false);
		// listUrls.setRemoveAllWhenShown(true);
		this.prevContextMenu = prevContextMenu;
	}

	@Override
	public void run() {
		((FeatureDiagramViewer) viewer).getControl().addMouseMoveListener(new MouseMoveListener() {

			@Override
			public void mouseMove(MouseEvent e) {
				((FeatureDiagramViewer) viewer).setContextMenu(prevContextMenu);
			}
		});
		createUrlList();
		((FeatureDiagramViewer) viewer).setContextMenu(listUrls);
		try {
			final Robot r = new Robot();
			r.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		} catch (final AWTException e) {
			e.printStackTrace();
		}
	}

	private void createUrlList() {
		final String urls = feature.getProperty().getUrls();
		if ((urls == null) || urls.isBlank()) {
			return;
		}
		final String[] us = urls.split("\n");
		for (final String u : us) {
			if (u.isBlank()) {
				continue;
			}
			final String[] splitUrl = u.split(" - ");
			if (splitUrl.length > 2) {
				continue;
			}
			final String urlLabel = splitUrl[0];
			String url = null;
			if (splitUrl.length == 1) {
				url = splitUrl[0];
			} else if (splitUrl.length == 2) {
				url = splitUrl[1];
			}
			listUrls.add(new OpenFeatureUrlAction(viewer, featureModelManager, urlLabel, url));
		}
	}

	public MenuManager getListUrlsMenu() {
		createUrlList();
		return listUrls;

	}

	@Override
	protected void updateProperties() {
		setEnabled(true);
	}
}
