package net.apogames.apogame;

import net.apogames.apogame.ApoGameConstants;
import net.apogames.apogame.entity.ApoButton;
import net.apogames.apogame.game.ApoGameEditor;
import net.apogames.apogame.game.ApoGameMenu;
import net.apogames.apogame.game.ApoGamePanel;
import net.apogames.apogame.game.ApoGamePuzzleChooser;
import net.apogames.apogame.game.ApoGamePuzzleGame;

public class ApoGameButtons {
	
	private final ApoGamePanel game;
	
	public ApoGameButtons(final ApoGamePanel game) {
		this.game = game;
	}

	public void init() {
		if (this.game.getButtons() == null) {
			this.game.setButtons(new ApoButton[ApoGameConstants.NUM_BUTTONS]);

			// Menu
			
			String function = ApoGameMenu.QUIT;
			int width = 200;
			int height = 60;
			int x = ApoGameConstants.GAME_WIDTH/2 - width/2;
			int y = ApoGameConstants.GAME_HEIGHT - 1 * height - 5;
			this.game.getButtons()[0] = new ApoButton(null, x, y, width, height, function);

			function = ApoGameMenu.PUZZLE;
			width = 300;
			height = 60;
			x = ApoGameConstants.GAME_WIDTH/2 - width/2;
			y = 150;
			this.game.getButtons()[1] = new ApoButton(null, x, y, width, height, function);
			
			function = ApoGameMenu.USERLEVELS;
			width = 300;
			height = 60;
			x = ApoGameConstants.GAME_WIDTH/2 - width/2;
			y = 150 + height * 1 + 20 * 1;
			this.game.getButtons()[2] = new ApoButton(null, x, y, width, height, function);
			


			this.game.getButtons()[2].setVisible(false);
			
			
			function = ApoGameMenu.EDITOR;
			width = 300;
			height = 60;
			x = ApoGameConstants.GAME_WIDTH/2 - width/2;
			y = 150 + height * 2 + 20 * 2;
			this.game.getButtons()[3] = new ApoButton(null, x, y, width, height, function);
			


			this.game.getButtons()[3].setVisible(false);
			
			
			// Puzzle Chooser
			
			function = ApoGamePuzzleChooser.BACK;
			width = 70;
			height = 40;
			x = ApoGameConstants.GAME_WIDTH - width - 5;
			y = ApoGameConstants.GAME_HEIGHT - 1 * height - 10;
			this.game.getButtons()[4] = new ApoButton(null, x, y, width, height, function);
			
			// Puzze Game
			
			function = ApoGamePuzzleGame.BACK;
			width = 70;
			height = 40;
			



			
			x = ApoGameConstants.GAME_WIDTH - width - 5;
			y = ApoGameConstants.GAME_HEIGHT - 1 * height - 10;
			
			this.game.getButtons()[5] = new ApoButton(null, x, y, width, height, function);
			
			// Editor
			
			


















































			
			function = ApoGameEditor.BACK;
			width = 70;
			height = 40;
			x = ApoGameConstants.GAME_WIDTH - width - 5;
			y = ApoGameConstants.GAME_HEIGHT - 1 * height - 10;
			this.game.getButtons()[6] = new ApoButton(null, x, y, width, height, function);
			
			function = ApoGameEditor.NEW;
			width = 70;
			height = 40;
			x = ApoGameConstants.GAME_WIDTH - 4 * width - 10 * 4;
			y = ApoGameConstants.GAME_HEIGHT - 1 * height - 10;
			this.game.getButtons()[7] = new ApoButton(null, x, y, width, height, function);
			
			function = ApoGameEditor.TEST;
			width = 70;
			height = 40;
			x = ApoGameConstants.GAME_WIDTH - 3 * width - 10 * 3;
			y = ApoGameConstants.GAME_HEIGHT - 1 * height - 10;
			this.game.getButtons()[8] = new ApoButton(null, x, y, width, height, function);
			
			function = ApoGameEditor.UPLOAD;
			width = 70;
			height = 40;
			x = ApoGameConstants.GAME_WIDTH - 2 * width - 10 * 2;
			y = ApoGameConstants.GAME_HEIGHT - 1 * height - 10;
			this.game.getButtons()[9] = new ApoButton(null, x, y, width, height, function);
			this.game.getButtons()[9].setVisible(false);
			
			function = ApoGameEditor.SOLVE;
			width = 70;
			height = 20;
			x = ApoGameConstants.GAME_WIDTH - width - 10;
			y = 2;
			this.game.getButtons()[10] = new ApoButton(null, x, y, width, height, function);
			
			
			for (int i = 0; i < this.game.getButtons().length; i++) {
				this.game.getButtons()[i].setBOpaque(true);
			}
		}
	}
}
