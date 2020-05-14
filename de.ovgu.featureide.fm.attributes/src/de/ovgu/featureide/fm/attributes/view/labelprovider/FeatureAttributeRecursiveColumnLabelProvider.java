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
package de.ovgu.featureide.fm.attributes.view.labelprovider;

import java.util.HashMap;

import org.eclipse.swt.graphics.Image;

import de.ovgu.featureide.fm.attributes.base.IFeatureAttribute;
import de.ovgu.featureide.fm.attributes.view.FeatureAttributeView;

/**
 * Label provider for the recursive attribute.
 * 
 * @author Joshuas Sprey
 * @author Chico Sundermann
 */
public class FeatureAttributeRecursiveColumnLabelProvider extends FeatureAttributeColumnLabelProvider {

	public FeatureAttributeRecursiveColumnLabelProvider(HashMap<String, Image> cachedImages, FeatureAttributeView view) {
		super(cachedImages, view);
	}

	@Override
	public String getText(Object element) {
		return "";
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof IFeatureAttribute) {
			if (((IFeatureAttribute) element).isRecursive()) {
				return cachedImages.get(FeatureAttributeView.checkboxEnabled);
			} else {
				return cachedImages.get(FeatureAttributeView.checkboxDisabled);
			}
		}
		return null;
	}
}
