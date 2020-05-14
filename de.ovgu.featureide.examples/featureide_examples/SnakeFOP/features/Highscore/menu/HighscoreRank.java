package menu;

/**
 * Speichert den Namen und die zugeh�rigen Punkte.
 * 
 * @author Alexander Grebhahn
 * @author Reimar Schr�ter
 * 
 * @version 1.0
 * 
 * @see Highscore
 */
public class HighscoreRank {
	private final String name;
	private final int points;
	
	/**
	 * Erstellt einen neuen Highscore-Eintrag.
	 * 
	 * @param name Name zum zugeh�rigen Punktwert
	 * @param points Punkte die erreicht wurden
	 */
	public HighscoreRank(String name, int points) {
		this.name = name;
		this.points = points;
	}
	
	/**
	 * Gibt die Punkte zur�ck.
	 * 
	 * @return erreichte Punkte
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Gibt den Namen zur�ck.
	 * 
	 * @return Name des Highscore-Eintrags
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name + "\n" + points + "\n";
	}

}
