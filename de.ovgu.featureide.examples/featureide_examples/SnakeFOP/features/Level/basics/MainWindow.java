package basics;

import java.util.LinkedList;

public class MainWindow extends JFrame {
	/** Namen der Level, die in dem Spiel existieren  */
	private static final LinkedList<String> nameLevel = new LinkedList<String>();
	
	/**
	 * F�gt neue Level hinzu.
	 */
	/**{@feature 0}
	 * Hook method.
	 */
	private static void addLevelNames() {}

	private String levelName = null;
	
	/**{@feature 0}
	 * L�dt die Levelnamen.
	 */
	public void startGame() {
		addLevelNames();
		original();
	}
}
