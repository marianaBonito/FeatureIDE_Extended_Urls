package entitys.util;

import basics.field.Level;

/**
 * Das Interface f�r eine nicht spielbare Entit�t.
 * 
 * @author Reimar Schr�ter
 * @author Alexander Grebhahn
 * 
 * @version 1.0
 * 
 * @see IEntity
 */
public interface IKIEntity extends IEntity {
	/**
	 * L�sst die Entit�t einen Zug machen.
	 * 
	 * @param level das aktuelle Level
	 */
	public void oneStep(Level level);
	
	/**
	 * Berechnet die Punkte f�r das Essen eines Teils der Entit�t.
	 * 
	 * @return die Punkte
	 */
	public int getPoints();
	
	/**
	 * T�tet die Entit�t.
	 * 
	 * @see #isAlive()
	 */
	public void kill();
	
	/**
	 * Gibt zur�ck, ob die Entit�t noch am Leben ist.
	 * 
	 * @return
	 * 	{@code true} falls die Enit�t lebt,</br>
	 * 	{@code false} falls nicht.
	 * 
	 * @see #kill()
	 */
	public boolean isAlive();
}
