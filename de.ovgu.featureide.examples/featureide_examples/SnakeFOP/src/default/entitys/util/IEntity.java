package entitys.util; 

/**
 * Das Interface aller Entit�ten.
 *
 * @author Reimar Schr�ter
 * @author Alexander Grebhahn
 * 
 * @version 1.0
 * 
 * @see IEntityPart
 */
public  interface  IEntity  extends Iterable<IEntityPart> {
	
	
	/**
     * Arten der Entit�ten.
     * 
     * @see IEntity#getType()
     */
	public static final int SNAKE = 0;

	
	public static final int BUG = 1;

	
	public static final int SLUG = 2;

	
	public static final int CENTIPEDE = 3;

	
	public static final int MOUSE = 4;

	
	public static final int FLY = 5;

	

	/**
	 * Gibt den Kopf der Entit�t zur�ck.
	 * 
	 * @return der Kopf der Entit�t
	 * 
	 * @see #getTail()
	 */
	public IEntityPart getHead();

	
	
	/**
	 * Gibt das letzte Teil der Entit�t zur�ck.</br>
	 * Liefert dasselbe Ergebnis wie {@link #getHead()}, wenn die Entit�t nur aus einem Teil besteht.
	 * 
	 * @return das letzte Teil der Entit�t
	 * 
	 * @see #getHead()
	 */
	public IEntityPart getTail();

	
	
	/**
     * Gibt die Art der Entit�t zur�ck.
     *
     * @return die Art der Entit�t
     */
    public int getType();


}
