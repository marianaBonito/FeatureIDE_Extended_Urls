package entitys.util;

/**
 * Das Interface eines Teilst�cks einer Entit�t.
 * 
 * @author Reimar Schr�ter,
 * @author Alexander Grebhahn
 * 
 * @version 1.0
 * 
 * @see IEntity
 */
public interface IEntityPart {
	/**
	 * T�tet das Teilst�ck.
	 * 
	 * @see #isAlive()
	 */
	public void eat();

	/**
	 * Die Richtung gibt an, wohin sich das Teilst�ck bewegt.
	 * 
	 * @return die Richtung des Teilst�cks
	 */
	public int getRoute();
	
	/**
	 * Der Status dient zur Darstellung der Teilst�cke. 
	 * 
	 * @return der Status des Teilst�cks
	 */
	public int getStatus();

	/**
	 * 
	 * @return die X-Position des Teilst�cks
	 * 
	 * @see #getYPos()
	 */
	public int getXPos();

	/**
	 * 
	 * @return die Y-Position des Teilst�cks
	 * 
	 * @see #getXPos()
	 */
	public int getYPos();
	
	/**
	 * Gibt an, ob das Teilst�ck der Entit�t lebendig ist.
	 * 
	 * @return
	 * 	{@code true} falls das Teilst�ck lebt,</br>
	 * 	{@code false} falls nicht.
	 * 
	 * @see #eat()
	 */
	public boolean isAlive();
}
