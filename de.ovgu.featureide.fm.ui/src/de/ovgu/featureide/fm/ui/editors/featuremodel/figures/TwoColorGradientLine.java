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
package de.ovgu.featureide.fm.ui.editors.featuremodel.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.graphics.Color;

import de.ovgu.featureide.fm.ui.editors.featuremodel.GUIDefaults;

/**
 * Represents a bar that got a two color gradient as background.
 *
 * @author Joshua Sprey
 */
public class TwoColorGradientLine extends Shape implements GUIDefaults {

	private final Color left;
	private final Color right;

	/**
	 * Creates a new color gradient that has two colors. One on the left and the other on the right side.
	 *
	 * @param left Color of the left side
	 * @param right Color of the right side
	 * @param width Width of the gradient
	 * @param height Height of the gradient
	 */
	public TwoColorGradientLine(Color left, Color right, int width, int height) {
		final XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		setSize(width, height);
		setBackgroundColor(left);

		this.left = left;
		this.right = right;
		setOpaque(true);
	}

	@Override
	protected void fillShape(Graphics graphics) {
		// TODO Auto-generated method stub
		final Color oldFore = graphics.getForegroundColor();
		final Color oldBack = graphics.getBackgroundColor();

		graphics.setForegroundColor(left);
		graphics.setBackgroundColor(right);
		graphics.fillGradient(getLocation().x, getLocation().y, getSize().width, getSize().height, false);
		graphics.setForegroundColor(oldFore);
		graphics.setBackgroundColor(oldBack);
	}

	@Override
	protected void outlineShape(Graphics graphics) {}
}
