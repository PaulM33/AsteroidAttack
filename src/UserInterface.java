
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * Program Name: UserInterface.java
 * @author Daniel Krauskopf
 * Purpose:
 * Date 14-Aug-2016
 */
public class UserInterface implements Drawable {
    private final AAListener listener;
    
    private int nukeProgress;
    private int score;
    
    public UserInterface(AAListener listener) {
        this.listener = listener;
        nukeProgress = 0;
        score = 0;
    } // UserInterface (AAListener)
    
    public void addScore() {
        if (nukeProgress < 10)
            nukeProgress++;
        score++;
    } // addScore ();
    
    public boolean canNuke() {
        return nukeProgress  == 10;
    } // canNuke ();
    
    public void fireNuke() {
        nukeProgress = 0;
    } // fireNuke ();

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("" + score, 50, 100);
        g.drawOval(250, 800 - 75, 300, 300);
        
        g.drawLine(400, 800, listener.mouse_postion.x, listener.mouse_postion.y);
        
        g.drawRect(50, 350, 10, 100);
        g.fillRect(50, 450 - nukeProgress * 10, 10, nukeProgress * 10);
    } // draw (Graphics);

    @Override
    public boolean shouldDestory() {
        return false;
    } // shouldDestory ();
} // UserInterface;
