package net.apogames.apogame.level;

public class ApoGameLevel {

	



























































































































































	
	/**
	 * 0 = empty
	 * 1 = goal
	 * 2 = dice with no moves
	 * 3 = dice with 1 moves
	 * 4 = dice with 2 moves
	 * 5 = dice with 3 moves
	 * 6 = dice with 4 moves
	 * 7 = dice with 5 moves
	 * 8 = dice with 6 moves
	 * a = dice with no moves and goal down
	 * b = dice with 1 moves and goal down
	 * c = dice with 2 moves and goal down
	 * d = dice with 3 moves and goal down
	 * e = dice with 4 moves and goal down
	 * f = dice with 5 moves and goal down
	 * g = dice with 6 moves and goal down
	 */
	private static final String[] levelsString = new String[] {
		
		
		"00000000"+
		"00000000"+
		"04000050"+
		"00100000"+
		"00000100"+
		"00310000"+
		"00000000"+
		"00000000",
		
		"0000000000000000000000000040000000311000000000000000000000000000",
		
		"00000000" +
		"00000000" +
		"00000000" +
		"00040000" +
		"00103000" +
		"00010000" +
		"00000000" +
		"00000000",
		
		"0000000000000000000000000013100000000000004150000000000000000000",
		
		"00000000"+
		"00000000"+
		"00031000"+
		"00041000"+
		"00015000"+
		"00016000"+
		"00000000"+
		"00000000",
		
		
		
























































































		































































	};
	
	
	public static String[] editorLevels = null;
	
	public static final String getLevel(int level) {
		if ((level < 0) || (level >= levelsString.length)) {
			return null;
		}
		return levelsString[level];
	}
	
	public static final int MAX_LEVELS = levelsString.length;
	
	public static boolean isIn(String level) {
		for (int i = 0; i < levelsString.length; i++) {
			if (level.equals(levelsString[i])) {
				return true;
			}
		}
		return false;
	}
}
