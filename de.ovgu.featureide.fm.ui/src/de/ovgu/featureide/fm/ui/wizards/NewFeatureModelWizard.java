/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2019  FeatureIDE team, University of Magdeburg, Germany
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
package de.ovgu.featureide.fm.ui.wizards;

import static de.ovgu.featureide.fm.core.localization.StringTable.NEW_FEATURE_MODEL;
import static de.ovgu.featureide.fm.core.localization.StringTable.NEW_FILE_WAS_NOT_ADDED_TO_FILESYSTEM;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.ovgu.featureide.fm.core.ExtensionManager.NoSuchExtensionException;
import de.ovgu.featureide.fm.core.Logger;
import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.base.impl.DefaultFeatureModelFactory;
import de.ovgu.featureide.fm.core.base.impl.FMFactoryManager;
import de.ovgu.featureide.fm.core.io.EclipseFileSystem;
import de.ovgu.featureide.fm.core.io.IPersistentFormat;
import de.ovgu.featureide.fm.core.io.manager.SimpleFileHandler;
import de.ovgu.featureide.fm.ui.FMUIPlugin;

/**
 * A Wizard to create a new Feature Model file.
 *
 * @author Jens Meinicke
 * @author Marcus Pinnecke
 * @author Sebastian Krieter
 */
// TOOD add copy of an other model file
public class NewFeatureModelWizard extends Wizard implements INewWizard {

	public static final String ID = FMUIPlugin.PLUGIN_ID + ".wizard.NewFeatureModelWizard";

	private NewFeatureModelFileLocationPage locationpage;
	private NewFeatureModelFileFormatPage formatPage;

	@Override
	public boolean performFinish() {
		final IPersistentFormat<IFeatureModel> format = formatPage.getFormat();
		final Path fmPath = getNewFilePath(format);
		IFeatureModel featureModel;
		try {
			featureModel = FMFactoryManager.getInstance().getFactory(fmPath, format).create();
		} catch (final NoSuchExtensionException e) {
			Logger.logError(e);
			featureModel = DefaultFeatureModelFactory.getInstance().create();
		}
		featureModel.createDefaultValues("");
		SimpleFileHandler.save(fmPath, featureModel, format);

		assert (Files.exists(fmPath)) : NEW_FILE_WAS_NOT_ADDED_TO_FILESYSTEM;
		return true;
	}

	public Path getNewFilePath(IPersistentFormat<IFeatureModel> format) {
		String fileName = locationpage.getFileName();
		if (!fileName.matches(".+\\." + Pattern.quote(format.getSuffix()))) {
			fileName += "." + format.getSuffix();
		}
		return EclipseFileSystem.getPath(ResourcesPlugin.getWorkspace().getRoot().getFile(locationpage.getContainerFullPath().append(fileName)));
	}

	@Override
	public void addPages() {
		setWindowTitle(NEW_FEATURE_MODEL);
		addPage(locationpage);
		addPage(formatPage);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		formatPage = new NewFeatureModelFileFormatPage();
		locationpage = new NewFeatureModelFileLocationPage("location", selection);
	}

}
