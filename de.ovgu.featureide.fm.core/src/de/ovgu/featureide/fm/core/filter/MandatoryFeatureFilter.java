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
package de.ovgu.featureide.fm.core.filter;

import java.util.function.Predicate;

import de.ovgu.featureide.fm.core.base.FeatureUtils;
import de.ovgu.featureide.fm.core.base.IFeature;
import de.ovgu.featureide.fm.core.base.IFeatureStructure;

/**
 * Checks whether a feature is mandatory in an AND group.
 *
 * @author Sebastian Krieter
 */
public class MandatoryFeatureFilter implements Predicate<IFeature> {

	@Override
	public boolean test(IFeature object) {
		if (FeatureUtils.getRoot(object.getFeatureModel()).getName().equals(object.getName())) {
			return true;
		}
		final IFeatureStructure structure = object.getStructure();
		final IFeatureStructure parent = structure.getParent();
		return (parent == null) || (parent.isAnd() && structure.isMandatorySet()) || (!parent.isAnd() && (parent.getChildrenCount() == 1));
	}

}
