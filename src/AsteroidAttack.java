
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.Vector;
import javax.swing.JFrame;

/**
 * 
 * Program Name: AsteroidAttack.java
 * @author Daniel Krauskopf
 * Purpose:
 * Date 13-Aug-2016
 */
public class AsteroidAttack extends JFrame implements Runnable {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 800;
    
    private Image buffer;
    private boolean isRunning;
    private Vector<Drawable> drawable;
    
    public AsteroidAttack() {
        super("Asteroid Attack");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);
        
        buffer = this.createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        drawable = new Vector<>();
        
        this.setVisible(true);
    } // AsteroidAttack();
    
    @Override
    public void run() {
        drawable.add(new Asteroid(100, 100, 10, 1, 500));
        while (isRunning) {
            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                continue;
            }
            
            Graphics g = bs.getDrawGraphics();
            render(g);
            g.dispose();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (bs != null) {
                bs.show();
            }
            
        } // while(Running);
    } // run ();
    
    public void start() {
        isRunning = true;
        Thread t = new Thread(this);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    } // start ();
    
    public void stop() {
        isRunning = false;
    } // stop ();
    
    public void render(Graphics g) {
        for (Drawable d : drawable) {
            d.draw(g);
        }
    } // draw ();
    
    public void addDrawable(Drawable d) {
        drawable.add(d);
    } // addDrawable (Drawable);
    
    public void removeDrawable(Drawable d) {
        drawable.remove(d);
    } // removeDrawable (Drawable);
} // AsteroidAttack;
