package net.apogames.apogame;

import net.apogames.apogame.entity.ApoButton;
import net.gliblybits.bitsengine.gui.BitsButton;
import net.gliblybits.bitsengine.gui.BitsScreen;

import net.gliblybits.bitsengine.input.BitsInput;
import net.gliblybits.bitsengine.input.BitsKeyEvent;
import net.gliblybits.bitsengine.input.BitsKeyListener;
import net.gliblybits.bitsengine.input.BitsTouchEvent;
import net.gliblybits.bitsengine.input.BitsTouchListener;





import net.gliblybits.bitsengine.render.BitsGraphics;

public abstract class ApoGameComponent extends BitsScreen 
	implements BitsTouchListener, BitsKeyListener
{
	
	/** Array der ganzen Buttons im Spiel */
	private ApoButton[] buttons;

	private int oldX, oldY;
	
	


	
	private ApoGameModel model;
	
	protected ApoGameComponent(int id) {
		super(id);
	}

	public void onButtonPressed(BitsButton button) {
		
	}

	public ApoGameModel getModel() {
		return this.model;
	}

	public void setModel(ApoGameModel model) {
		this.model = model;
	}

	/**
	 * gibt das Array mit den Buttons zur�ck
	 * @return gibt das Array mit den Buttons zur�ck
	 */
	public final ApoButton[] getButtons() {
		return this.buttons;
	}

	/**
	 * setzt das Array mit den Buttons auf den �bergebenen Wert
	 * @param buttons : neues Array mit Buttons
	 */
	public void setButtons(ApoButton[] buttons) {
		this.buttons = buttons;
	}
	
	

































	
	public final void onTouchDown( final int pointerId, final float x, final float y, final BitsTouchEvent event ) {
		boolean bButton = false;
		if (this.getButtons() != null) {
			for (int b = 0; b < this.getButtons().length; b++) {
				if ((this.getButtons()[b].isBVisible()) && (this.getButtons()[b].intersects(x, y, 1, 1))) {
					String function = this.getButtons()[b].getFunction();
					this.setButtonFunction(function);
					bButton = true;
					break;
				}
			}
		}
		if (!bButton) {
			if (this.model != null) {
				this.model.touchedPressed((int)x, (int)y, pointerId);
			}
		}
		this.oldX = (int)x;
		this.oldY = (int)y;
	}

	public final void onTouchUp( final int pointerId, final float x, final float y, final BitsTouchEvent event ) {
		if (this.model != null) {
			this.model.touchedReleased((int)x, (int)y, pointerId);
		}     	
	}

	public final void onTouchDragged( final int pointerId, final float x, final float y, final BitsTouchEvent event ) {
		if ((this.model != null) && (((int)(x) != this.oldX) || ((int)(y) != this.oldY))) {
			this.model.touchedDragged((int)(x), (int)(y), this.oldX, this.oldY, pointerId);
		}
		
		this.oldX = (int)x;
		this.oldY = (int)y;
	}
	
	
	/**
	 * rendert die Buttons
	 * @param g : das Graphics2D Object
	 */
	public void renderButtons(BitsGraphics g) {
		if (this.buttons != null) {
			for (int i = 0; i < this.buttons.length; i++) {
				this.buttons[i].render(g, 0, 0);
			}
		}
	}

	/**
	 * wird aufgerufen, wenn ein Button gedr�ckt wurde
	 * @param function : Funktion, die der Button ausf�hren soll und ihn einzigartig macht
	 */
	public abstract void setButtonFunction(String function);
	
	@Override
	public void onInit() {
		BitsInput.getIt().registerTouchListener(this);
		init();
	}
	
	public void init() {
		
	}

	@Override
	public void onPause() {
		
	}

	@Override
	public void onResume() {
		
	}

	@Override
	public void onFinish() {
		
	}

	@Override
	public void onBackButtonPressed() {
		
	}
	
	public void onKeyDown(final int key, final BitsKeyEvent event) {
		
		if (this.model != null) {
			this.model.onKeyDown(key);
		}
		
	}

	public void onKeyUp(final int key, final BitsKeyEvent event) {
		
		if (this.model != null) {
			this.model.onKeyUp(key);
		}
		
	}
	
}
