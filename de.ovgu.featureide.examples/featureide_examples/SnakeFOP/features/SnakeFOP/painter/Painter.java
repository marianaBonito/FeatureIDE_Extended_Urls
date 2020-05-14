package painter;

import java.awt.Image;

import basics.field.Level;
import entitys.util.IEntity;

/**
 * Organisation der anderen beiden Painter-Klassen.
 * 
 * @author Alexander Grebhahn
 * @author Reimar Schr�ter
 * 
 * @version 1.0
 */
public class Painter {
	private final LevelPainter levelPainter;
	private final EntityPainter entityPainter;

	/**
	 * Erstellt einen neuen Zeichner.
	 * 
	 * @param xPicture Breite des zu zeichnenden Images
	 * @param yPicture H�he des zu zeichnenden Images
	 */
	public Painter(int xPicture, int yPicture) {
		levelPainter = new LevelPainter(xPicture, yPicture);
		entityPainter = new EntityPainter(xPicture, yPicture);
	}

	/**
	 * Bereitet den Zeichner auf ein neues Level vor.
	 */
	public void newLevel() {
		levelPainter.newLevel();
	}

	/**
	 * Zeichnet das gegebene Level und alle Entit�ten.
	 * 
	 * @param level das aktuelle Level
	 * @param entities die Entit�ten, die gemalt werden sollen
	 *            
	 * @return das erstellte {@link Image}-Objekt
	 * 
	 * @see LevelPainter#paintField(Level)
	 * @see EntityPainter#paintEntitys(Image, IEntity[])
	 */
	public Image paintFrame(Level level, IEntity[] entities) {
		return entityPainter.paintEntitys(levelPainter.paintField(level), entities);
	}
}
