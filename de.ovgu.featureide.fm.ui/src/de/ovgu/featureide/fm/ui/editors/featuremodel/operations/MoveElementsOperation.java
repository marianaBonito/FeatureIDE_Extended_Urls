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
package de.ovgu.featureide.fm.ui.editors.featuremodel.operations;

import static de.ovgu.featureide.fm.core.localization.StringTable.DELETE;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.base.event.FeatureIDEEvent;
import de.ovgu.featureide.fm.core.io.manager.FeatureModelManager;
import de.ovgu.featureide.fm.core.io.manager.IFeatureModelManager;
import de.ovgu.featureide.fm.ui.editors.FeatureModelEditor;
import de.ovgu.featureide.fm.ui.editors.featuremodel.GUIDefaults;
import de.ovgu.featureide.fm.ui.views.outline.standard.FmOutlinePage;

/**
 * Operation with functionality to move multiple elements from the {@link FeatureModelEditor} and the {@link FmOutlinePage}. Enables Undo/Redo.
 *
 * @author Fabian Benduhn
 * @author Marcus Pinnecke
 * @author Sebastian Krieter
 */
public class MoveElementsOperation extends AbstractFeatureModelOperation implements GUIDefaults {

	private final Deque<AbstractFeatureModelOperation> operations = new LinkedList<>();

	public MoveElementsOperation(IFeatureModelManager featureModelManager) {
		super(featureModelManager, DELETE);
	}

	@Override
	protected FeatureIDEEvent operation(IFeatureModel featureModel) {
		for (final Iterator<AbstractFeatureModelOperation> it = operations.iterator(); it.hasNext();) {
			final AbstractFeatureModelOperation operation = it.next();
			operation.redo();
		}
		return null;
	}

	@Override
	protected FeatureIDEEvent inverseOperation(IFeatureModel featureModel) {
		for (final Iterator<AbstractFeatureModelOperation> it = operations.descendingIterator(); it.hasNext();) {
			final AbstractFeatureModelOperation operation = it.next();
			operation.undo();
		}
		return null;
	}

	@Override
	protected int getChangeIndicator() {
		return FeatureModelManager.CHANGE_DEPENDENCIES;
	}

}
