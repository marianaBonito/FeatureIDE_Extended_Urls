package basics.util;

/**
 * Listener f�r Tastatureingaben.
 * 
 * @author Alexander Grebhahn
 * @author Reimar Schr�ter
 * 
 * @version 1.0
 * 
 * @see basics.field.GameField
 * @see menu.MainMenu
 */
public interface IPanelListener {
	/**
	 * Behandelt gedr�ckte Tasten.
	 * 
	 * @param keyCode der Code der gedr�ckten Taste
	 */
	public void keyPressed(int keyCode);
}
