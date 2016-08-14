
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 * Program Name: Images.java
 * @author Daniel Krauskopf
 * Purpose: To store and use all of the image files.
 * https://www.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
 * Date 14-Aug-2016
 */
public enum Images {
    // Picture from: http://clipart.me
    NUKE("Pictures/nuke-new-tr.png"), 
    // Images from: http://www.clipartpanda.com/clipart_images/related-cliparts-62559252
    EXPLOSION("Pictures/explosion.jpg");
    
    public BufferedImage image;

    private Images(String fileName) {
        try {
            image = ImageIO.read(getClass().getResource(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Images(String);
    
    static void init() {
        values();
    } // init ();
} // Images;
