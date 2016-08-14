
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * Program Name: AsteroidAttack.java
 * @author Daniel Krauskopf 
 * Purpose: The Main program of Asteroid Attack.
 * Date 13-Aug-2016
 */
public class AsteroidAttack extends JFrame implements Runnable {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 800;

    private boolean isRunning;
    private Vector<Drawable> drawable;
    private AAListener listener;
    private UserInterface ui;
    private int counter;
    private boolean dirFlag;

    public AsteroidAttack() {
        super("Asteroid Attack");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLocationRelativeTo(null);

        drawable = new Vector<>();
        listener = new AAListener();
        ui = new UserInterface(listener);
        counter = 0;
        dirFlag = false;

        this.setVisible(true);
    } // AsteroidAttack();

    /**
     * Runs the Thread.
     */
    @Override
    public void run() {
        addDrawable(ui); // Add the main User Interface.

        while (isRunning) {
            // Use a buffer Strategy to use the best buffering based on the system.
            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                continue;
            }

            // Get the Graphics and render to the screen.
            Graphics g = bs.getDrawGraphics();
            render(g);
            g.dispose();

            // Slow down the program, to make it playable...
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Show the frame.
            if (bs != null)
                bs.show();
        } // while(Running);
    } // run ();

    /**
     * Starts and sets up the application.
     */
    public void start() {
        this.addMouseListener(listener);
        this.addKeyListener(listener);
        this.addMouseMotionListener(listener);
        isRunning = true;
        SoundEffect.init(); // Load all the sounds.
        Images.init(); // Load all the images.
        Menu.setMenu(Menu.START);
        Thread t = new Thread(this);
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();
    } // start ();

    /**
     * Stop the Thread.
     */
    public void stop() {
        isRunning = false;
    } // stop ();

    /**
     * Draws to the Graphics context.
     * @param g The Graphics to draw to.
     */
    public void render(Graphics g) {
        // Clear the screen.
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        Font defaultFont = g.getFont();

        if (Menu.is(Menu.GAME)) {
            Point p = new Point(0, 0);
            // Add a new Asteroid.
            if (Helper.rand(0, 100) <= 1)
                addDrawable(new Asteroid());

            if (listener.space_pressed) {
                // Fire Nuke... And only allow one to exist at a time.
                if (!Nuke.exists && ui.canNuke()) {
                    ui.fireNuke();
                    addDrawable(new Nuke(drawable));
                }
            } // if(Space Pressed);
            
            if (listener.mouse_clicked) {
                // Extend the line!
                p = Helper.increaseLine(new Point(400, 800), listener.laser_position, 800);
                g.setColor(Color.RED);
                g.drawLine(p.x, p.y, 400, 800);
                SoundEffect.LASER.play();
            } // if(mouse Clicked);

            // A for loop for everything that is drawable.
            for (int i = 0; i < drawable.size(); i++) {
                Drawable d = drawable.get(i);                
                d.draw(g);

                // If its an Asderoid...
                if (d instanceof Asteroid && listener.mouse_clicked) {
                    Asteroid a = (Asteroid) d;
                    if(a.checkHit(p)) {
                        if (a.isTwin) {
                            addDrawable(new Asteroid(a, -a.radius));
                            addDrawable(new Asteroid(a, a.radius));
                        }
                        removeDrawable(d);
                        ui.addScore();
                    } // if(a is hit);
                } // if(Asteroid && Clicked);

                // If the Drawable should stop being drawn and cleaned up.
                if (d.shouldDestory()) 
                    removeDrawable(d);
            } // for(i);
        } // if(Menu: Game);
        else if(Menu.is(Menu.WIN)) {
            SoundEffect.CHERRING.play();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Serif", Font.PLAIN, 25));
            g.drawString("You Win, Press space to play again.", 225, 400);
            if (listener.space_pressed)
                resetGame();
        } // if(Menu: Win);
        else if(Menu.is(Menu.LOSE)) {
            SoundEffect.BOO.play();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Serif", Font.PLAIN, 25));
            g.drawString("You lose. Press space to play again.", 225, 400);
            if (listener.space_pressed)
                resetGame();
        } // if(Menu: Lose);
        else if(Menu.is(Menu.START)) {
            // TODO: Select Game mode... and other settings...
            g.setColor(Color.WHITE);
            
            g.setFont(new Font("Serif", Font.PLAIN, 100));
            g.drawString("Asteroid Attack", 75, 150);
            
            g.setFont(new Font("Sans-Serif", Font.PLAIN, 25));
            g.drawString("Shoot the asteroids with the laser!", 225, 200);
            g.drawString("Press Space to fire the nuke.", 250, 250);
            
            g.drawImage(
                    Images.ASTEROID.image, 
                    400 - Images.ASTEROID.image.getWidth() / 2, 
                    (400 - Images.ASTEROID.image.getHeight() / 2) + 75, 
                    null
            );
            
            g.setFont(defaultFont);
            
            // Strobe the text to give focus.
            if (dirFlag) {
                if (counter >= 20) {
                    dirFlag = false;
                } else {
                    counter++;
                    g.drawString("Press Space to start", 350, 750);
                }
            } else {
                if (counter <= 0) {
                    dirFlag = true;
                } else {
                    counter--;
                }
            }
            
            if (listener.space_pressed)
                Menu.setMenu(Menu.GAME);
        } // if(Menu: Start);
    } // draw ();
    
    /**
     * Resets all the game variables.
     */
    public void resetGame() {
        for (int i = drawable.size() - 1; i >= 0; i--) {
            if (!(drawable.get(i) instanceof UserInterface)) {
                drawable.remove(i);
            }
        }
        ui.reset();
        Menu.setMenu(Menu.GAME);
    } // resetGame ();

    /**
     * Add a Drawable to the Vector.
     * @param d The Drawable to add.
     */
    public void addDrawable(Drawable d) {
        drawable.add(d);
    } // addDrawable (Drawable);

    /**
     * Remove a Drawable from the Vector.
     * @param d The Drawable to remove.
     */
    public void removeDrawable(Drawable d) {
        drawable.remove(d);
    } // removeDrawable (Drawable);
} // AsteroidAttack;
