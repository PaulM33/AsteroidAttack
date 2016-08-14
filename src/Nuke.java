
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * Program Name: Nuke.java
 * @author Daniel Krauskopf
 * Purpose:
 * Date 14-Aug-2016
 */
public class Nuke implements Drawable {
    static boolean exists = false;
    
    private boolean shouldDestory;
    private Point position;
    private BufferedImage image;
    
    private int imageOffsetX;
    private int imageOffsetY;

    public Nuke() {
        Nuke.exists = true;
        shouldDestory = false;
        position = new Point(400, 800);
        
        // Picture from: http://clipart.me
        try {
            image = ImageIO.read(getClass().getResource("Pictures/nuke-new-tr.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        imageOffsetX = image.getHeight() / 2;
        imageOffsetY = image.getWidth() / 2;
        SoundEffect.NUKE.play();
    } // Nuke();

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, position.x - imageOffsetX, position.y - imageOffsetY, null);
        position.translate(0, -10);
        
        
        if (position.y <= 400) {
            // Kill the nuke and all astroids...
            shouldDestory = true;
            Nuke.exists = false;
        }
    } // draw (Graphics g);

    @Override
    public boolean shouldDestory() {
        return shouldDestory;
    } // shouldDestory ();
} // Nuke;
