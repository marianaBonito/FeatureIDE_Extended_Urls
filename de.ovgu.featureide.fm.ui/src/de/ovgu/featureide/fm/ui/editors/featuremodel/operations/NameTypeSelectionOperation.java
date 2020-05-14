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

import de.ovgu.featureide.fm.core.base.IFeatureModel;
import de.ovgu.featureide.fm.core.base.event.FeatureIDEEvent;
import de.ovgu.featureide.fm.core.base.event.FeatureIDEEvent.EventType;
import de.ovgu.featureide.fm.ui.editors.IGraphicalFeatureModel;
import de.ovgu.featureide.fm.ui.editors.featuremodel.layouts.FeatureDiagramLayoutHelper;

/**
 * Operation to select the layout for the feature model editor.
 */
public class NameTypeSelectionOperation extends AbstractGraphicalFeatureModelOperation {

	private final int newNameType;
	private final int oldNameType;

	public NameTypeSelectionOperation(IGraphicalFeatureModel graphicalFeatureModel, int newNameType, int oldNameType) {
		super(graphicalFeatureModel, FeatureDiagramLayoutHelper.getNameTypeLabel(newNameType));
		this.newNameType = newNameType;
		this.oldNameType = oldNameType;
	}

	@Override
	protected FeatureIDEEvent operation(IFeatureModel featureModel) {
		graphicalFeatureModel.getLayout().setShowShortNames(newNameType == 1);
		return FeatureIDEEvent.getDefault(EventType.ALL_FEATURES_CHANGED_NAME_TYPE);
	}

	@Override
	protected FeatureIDEEvent inverseOperation(IFeatureModel featureModel) {
		graphicalFeatureModel.getLayout().setShowShortNames(oldNameType == 1);
		return FeatureIDEEvent.getDefault(EventType.ALL_FEATURES_CHANGED_NAME_TYPE);
	}

}
