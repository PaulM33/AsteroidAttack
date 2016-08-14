
import java.awt.Graphics;

/**
 * 
 * Program Name: Drawable.java
 * @author Paul Magbor, Daniel Krauskopf
 * Purpose: The Drawable interface. All implementing object will be able to
 *          draw themselves to a Graphics context.
 * Date 13-Aug-2016
 */
public interface Drawable {
    /**
     * To Draw the item to the Graphics context.
     * @param g The Graphics context.
     */
    public void draw (Graphics g);
    
    /**
     * If the Drawable should be removed.
     * @return True if it should be removed.
     */
    public boolean shouldDestory();
}
