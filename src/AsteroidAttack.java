
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * Program Name: AsteroidAttack.java
 *
 * @author Daniel Krauskopf Purpose: Date 13-Aug-2016
 */
public class AsteroidAttack extends JFrame implements Runnable {

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 800;

    private boolean isRunning;
    private Vector<Drawable> drawable;
    private AAListener listener;
    
    int score = 0;

    Asteroid a1 = new Asteroid();
    Asteroid a2 = new Asteroid();

    public AsteroidAttack() {
        super("Asteroid Attack");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        drawable = new Vector<>();
        listener = new AAListener();

        this.setVisible(true);
    } // AsteroidAttack();

    @Override
    public void run() {
        //addDrawable(new Asteroid(100, 200, 50, 1, 5));
        addDrawable(a1);
        addDrawable(a2);

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
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (bs != null) {
                bs.show();
            }

        } // while(Running);
    } // run ();

    public void start() {
        this.addMouseListener(listener);
        this.addKeyListener(listener);
        isRunning = true;
        SoundEffect.init(); // Load all the sounds.
        Thread t = new Thread(this);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    } // start ();

    public void stop() {
        isRunning = false;
    } // stop ();

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if (Helper.rand(0, 1000) <= 1) {
            addDrawable(new Asteroid());
        }

        if (listener.space_pressed) {
            // Fire Nuke... And only allow one to exist at a time.
            if (!Nuke.exists)
                addDrawable(new Nuke());
        }

        if (!drawable.isEmpty()) {
            for (int i = 0; i < drawable.size(); i++) {
                Drawable d = drawable.get(i);
                d.draw(g);
                
                if (d instanceof Asteroid && listener.mouse_clicked) {
                    Point p = Helper.increaseLine(new Point(400, 800), listener.mouse_position, 800);
                    Asteroid a = (Asteroid) d;
                    g.setColor(Color.RED);
                    g.drawLine(p.x, p.y, 400, 800);
                    
                    SoundEffect.LASER.play();

                    if (Helper.lineIntercetCircle(
                            new Point(400, 800),
                            p,
                            a.getCenter(),
                            a.getRadius()
                    )) {
                        g.setColor(Color.WHITE);
                        //a.scoredHit();
                        if(a.scoredHit() == 0) {
                            removeDrawable(d);
                            score++;
                        }

                    }
                }
                
                if (d.shouldDestory())
                    removeDrawable(d);
                
            }
        }
        
        g.setColor(Color.WHITE);
        g.drawString("" + score, 100, 100);
    } // draw ();

    public void addDrawable(Drawable d) {
        drawable.add(d);
    } // addDrawable (Drawable);

    public void removeDrawable(Drawable d) {
        drawable.remove(d);
    } // removeDrawable (Drawable);
} // AsteroidAttack;
