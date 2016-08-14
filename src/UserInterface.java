
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 
 * Program Name: UserInterface.java
 * @author Daniel Krauskopf
 * Purpose: The Game Menu User interface.
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
    
    /**
     * Adds one to the score and increments the Nuke progress if it is less then 10.
     * Also when the score if more then 50 sets the menu to the Win Menu.
     */
    public void addScore() {
        if (nukeProgress < 10)
            nukeProgress++;
        score++;
        if (score >= 50)
            Menu.setMenu(Menu.WIN);
    } // addScore ();
    
    /**
     * If the nuke can be fired.
     * @return True if the Nuke can be fired.
     */
    public boolean canNuke() {
        return nukeProgress  == 10;
    } // canNuke ();
    
    /**
     * Resets the Nuke Progress.
     */
    public void fireNuke() {
        nukeProgress = 0;
    } // fireNuke ();

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        Font defaultFont = g.getFont();
        
        g.setFont(new Font("Sans-Serif", Font.PLAIN, 15));
        g.drawString("Score: " + score, 50, 100);
        g.drawOval(250, 800 - 75, 300, 300);
        
        //g.drawLine(400, 800, listener.mouse_postion.x, listener.mouse_postion.y);
        
        g.drawRect(50, 350, 10, 100);
        g.fillRect(50, 450 - nukeProgress * 10, 10, nukeProgress * 10);
        
        g.setFont(defaultFont);
        g.drawString("Nuke", 42, 475);
    } // draw (Graphics);

    @Override
    public boolean shouldDestory() {
        return false; // Never destory the User Interface.
    } // shouldDestory ();
    
    /**
     * Resets the Nuke progress and the score.
     */
    public void reset() {
        nukeProgress = 0;
        score = 0;
    } // reset ();
} // UserInterface;
