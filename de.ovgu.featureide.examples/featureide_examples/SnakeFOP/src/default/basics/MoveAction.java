package basics; 

import java.util.TimerTask; 

import basics.field.GameField; 

/**
 * Die Klasse bewirkt die Aktualisierung des Spielfelds in regelm��igen Zeitabst�nden.
 * 
 * @author Alexander Grebhahn
 * @author Reimar Schr�ter
 * 
 * @version 1.0
 * 
 * @see RepaintAction
 */
public  class  MoveAction  extends TimerTask {
	
	private final GameField gameField;

	 // GameField welches aktualisiert werden soll

	/**
	 * Erstellt eine Instanz der Klasse.
	 * 
	 * @param gameField das aktuelle Spielfeld
	 */
	public MoveAction(GameField gameField) {
		this.gameField = gameField;
	}

	

	@Override
	public void run() {
		gameField.move();
	}


}
