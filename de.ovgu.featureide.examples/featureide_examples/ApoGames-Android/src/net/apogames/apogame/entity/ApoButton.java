package net.apogames.apogame.entity;

import net.gliblybits.bitsengine.core.BitsImage;
import net.gliblybits.bitsengine.render.BitsGraphics;

/**
 * Diese Klasse handelt einen Button, die ein 3geteiltes Bild enth�lt
 * das erste wird angezeigt, wenn die Maus nicht dadr�ber ist
 * das zweite wird angezeigt, wenn die Maus �ber der Entity ist
 * das dritte wird angezeigt, wenn die Maus auf das Entity geklickt hat
 * @author Dirk Aporius
 *
 */
public class ApoButton extends ApoEntity {
	
	private int				WAIT_DELAY = 70;
	private int				wait, maxWait;
	private boolean			bWait, bFirstWait;
	private String			function;
	private boolean 		bOver, bPressed;
	
	public ApoButton( BitsImage iBackground, int x, int y, int width, int height, String function )	{
		super( iBackground, x, y, width, height );
		
		this.function	= function;
		this.bOver		= false;
		this.bPressed	= false;
		
		super.setBOpaque(false);
		this.wait		= 0;
		this.maxWait 	= 0;
		this.bWait		= false;
		this.bFirstWait	= true;
	}
	
//	/**
//	 * gibt das aktuelle Rechteck der Entity zur�ck
//	 * @return gibt das aktuelle Rechteck der Entity zur�ck
//	 */
//	public final Rect getRec() {
//		return this.rec;
//	}
	
	/**
	 * gibt zur�ck, ob wenn eine Maustaste gehalten wird, auch alle paar Millisekunden
	 * gecheckt werden soll, ob sich was ver�ndern soll
	 * @return gibt zur�ck, ob wenn eine Maustaste gehalten wird, auch alle paar Millisekunden
	 * gecheckt werden soll, ob sich was ver�ndern soll
	 */
	public boolean isBWait() {
		return this.bWait;
	}

	/**
	 * setzt den boolean Wert ob wenn die Maustaste gehalten wird, alle paar Millisekunden
	 * gecheckt werden soll, ob sich was ver�nder soll, auf den �bergebenen Wert
	 * @param bWait
	 */
	public void setBWait(boolean bWait) {
		this.bWait = bWait;
	}
	
	/**
	 * gibt die Wartezeit zwischen 2 Funktionsaufrufen, wenn die Maus
	 * gedr�ckt gehalten wird, zur�ck
	 * @return gibt die Wartezeit zwischen 2 Funktionsaufrufen, wenn die Maus
	 * gedr�ckt gehalten wird, zur�ck
	 */
	public int getWAIT_DELAY() {
		return this.WAIT_DELAY;
	}

	/**
	 * setzt die Wartezeit zwischen 2 Funktionsaufrufen auf den
	 * �bergebenen Wert
	 * @param wait_delay = neue Wartezeit in Millisekunden
	 */
	public void setWAIT_DELAY(int wait_delay) {
		this.WAIT_DELAY = wait_delay;
	}

	/**
	 * gibt zur�ck, ob die Maus �ber dem Button ist oder nicht
	 * @return TRUE, falls Maus dr�ber, sonst FALSE
	 */
	public boolean isBOver() {
		return this.bOver;
	}

	/**
	 * setzt den boolean-Wert f�r bOver auf den �bergebenen Wert
	 * @param bOver
	 */
	public void setBOver(boolean bOver) {
		this.bOver = bOver;
	}

	/**
	 * gibt zur�ck, ob eine Maustaste �ber dem Button gedr�ckt ist oder nicht
	 * @return TRUE, falls Maustaste gedr�ckt, sonst FALSE
	 */
	public boolean isBPressed()	{
		return this.bPressed;
	}

	/**
	 * setzt den boolean-Wert f�r bPressed auf den �bergebenen Wert 
	 * @param bPressed
	 */
	public void setBPressed(boolean bPressed) {
		this.bPressed = bPressed;
	}

	/**
	 * gibt die Funktion des Buttons zur�ck
	 * @return function
	 */
	public String getFunction()	{
		return this.function;
	}

	/**
	 * sezt die Funktion des Buttons auf den �bergebenen Wert
	 * @param function
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	
	/**
	 * was passiert, wenn die Maus im Spielfeld bewegt wurde
	 * @param x: x-Koordinate der Maus
	 * @param y: y-Koordinate der Maus
	 * @return TRUE, falls Maus dr�ber, sonst FALSE
	 */
	public boolean getMove( int x, int y ) {
		if ((!this.isBOver()) && (this.intersects(x, y)) && (this.isBVisible())) {
			this.setBOver( true );
			return true;
		} else if ((this.isBOver()) && (!this.intersects(x, y))) {
			this.notOver();
			return true;
		}
		return false;
	}
	
	public void setVisible(boolean bVisible) {
		super.setVisible(bVisible);
		if (!bVisible) {
			this.notOver();
		}
	}
	
	private void notOver() {
		this.bOver		= false;
		this.bPressed	= false;
		this.wait 		= 0;
		this.maxWait	= 0;
		this.bFirstWait	= true;
	}
	
	/**
	 * was passiert, wenn eine Maustaste im Spielfeld gedr�ckt wurde wurde
	 * @param x: x-Koordinate der Maus
	 * @param y: y-Koordinate der Maus
	 * @return TRUE, falls �ber Button Maus gedr�ckt, sonst FALSE
	 */
	public boolean getPressed( int x, int y ) {
		if ( ( this.isBOver() ) && ( this.intersects( x, y ) ) && ( this.isBVisible() ) ) {
			this.setBPressed( true );
			return true;
		}
		return false;
	}
	
	/**
	 * was passiert, wenn eine Maustaste im Spielfeld losgelassen wurde
	 * @param x: x-Koordinate der Maus
	 * @param y: y-Koordinate der Maus
	 * @return TRUE, wenn die Maustaste losgelassen wurde und der Spieler auch diesen Button gedr�ckt hatte, sonst FALSE
	 */
	public boolean getReleased( int x, int y ) {
		if ((this.isBPressed()) && (this.intersects(x, y)) && (this.isBVisible())) {
			this.setBPressed(false);
			this.setBOver(true);
			this.wait 		= 0;
			this.maxWait	= 0;
			this.bFirstWait	= true;
			return true;
		}
		return false;
	}
	
	public int getWait() {
		return this.wait;
	}

	/**
	 * was passiert, wenn eine Maustaste gedr�ckt wurde und gehalten wird
	 * @param delay
	 */
	public void think( int delay ) {
		if ( !this.isBWait() ) {
			return;
		}
		if ( this.isBPressed() ) {
			this.wait += delay;
			this.maxWait += delay;
			if ( this.bFirstWait ) {
				if ( this.wait > 400) {
					this.wait -= 400;
					this.bFirstWait = false;
					return;
				}
			} else {
				if (this.maxWait > 2500) {
					if (this.wait > this.WAIT_DELAY/5) {
						this.wait -= this.WAIT_DELAY/5;
					}
				} else {
					if (this.wait > this.WAIT_DELAY) {
						this.wait -= this.WAIT_DELAY;
					}
				}
			}
		}
	}
	
	/**
	 * malt den Button an die Stelle getX() + changeX und getY() + changeY hin
	 * @param changeX: Verschiebung in x-Richtung
	 * @param changeY: Verschiebung in y-Richtung
	 */
	public void render(BitsGraphics g, int changeX, int changeY ) {
		if ( this.isBVisible() ) {
			super.render(g, changeX, changeY);
		}
	}

}

