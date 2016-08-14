
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

/**
 * Program Name: Nuke.java
 * @author Daniel Krauskopf
 * Purpose: The Nuke object.
 * Date 14-Aug-2016
 */
public class Nuke implements Drawable {
    static boolean exists = false;
    
    private boolean shouldDestory;
    private Point position;
    private int imageOffsetX;
    private int imageOffsetY;
    private Vector<Drawable> drawable;

    public Nuke(Vector<Drawable> drawable) {
        Nuke.exists = true;
        shouldDestory = false;
        position = new Point(400, 800);
        
        this.drawable = drawable;
        imageOffsetX = Images.NUKE.image.getHeight() / 2;
        imageOffsetY = Images.NUKE.image.getWidth() / 2;
    } // Nuke();

    @Override
    public void draw(Graphics g) {
        g.drawImage(Images.NUKE.image, position.x - imageOffsetX, position.y - imageOffsetY, null);
        position.translate(0, -10); // Move the Nuke up.
        
        if (position.y <= 400) {
            SoundEffect.NUKE.play();
            g.drawImage( // Draw the explosion.
                    Images.EXPLOSION.image, 
                    400 - Images.EXPLOSION.image.getWidth() / 2, 
                    400 - Images.EXPLOSION.image.getHeight() / 2,
                    null
            );
            
            // Kill the nuke and all astroids...
            shouldDestory = true;
            for (int i = drawable.size() - 1; i >= 0; i--) {
                Drawable d = drawable.get(i);
                if (d instanceof Asteroid) {
                    drawable.remove(d);
                }
            } // for(i);
            Nuke.exists = false;
        } // if(Position is centre);
    } // draw (Graphics g);

    @Override
    public boolean shouldDestory() {
        return shouldDestory;
    } // shouldDestory ();
} // Nuke;
