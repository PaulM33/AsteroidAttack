
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Program Name: Asteroid.java 
 * Purpose: The creation of asteroids for the game
 * Author: Paul Magbor, Daniel Krauskopf
 * Date: 13-Aug-2016
 */
public class Asteroid implements Drawable {
    int xCoord;
    int yCoord;
    int velocity;
    int radius;
    final int initialHardness;
    int hardness;
    private Color colour;
    boolean isTwin;
    int coolDown;

    // Constructor
    public Asteroid() {
        this.xCoord = Helper.rand(50, 750);
        this.yCoord = 20;
        this.radius = Helper.rand(20, 50);
        this.velocity = Helper.rand(1, 5);
        this.initialHardness = Helper.rand(1, 4);
        this.hardness = initialHardness;
        this.colour = Color.BLUE;
        this.coolDown = 0;
        this.isTwin = Helper.rand(1, 100) <= 10;
    } // Asteroid();

    // Second constructor for twin
    public Asteroid(Asteroid mom, int deltaX) {
        this.xCoord = mom.xCoord + deltaX;
        this.yCoord = mom.yCoord;
        this.radius = mom.radius / 2;
        this.velocity = mom.velocity;
        this.initialHardness = mom.initialHardness;
        this.hardness = this.initialHardness;
        this.colour = Color.BLUE;
        this.isTwin = false;
        this.coolDown = 0;

    } // Asteroid(Asteroid, double);

    /**
     * Returns true if this Asteroid is a twin.
     * @return boolean.
     */
    public boolean isTwin() {
        return Helper.rand(1, 100) <= 10;
    } // isTwin ();

    /**
     * Updates the position of the Astroid.
     */
    public void updatePos() {
        yCoord = yCoord + velocity;
    } // updatePos ();
    
    // Getters:
    double getX() {
        return xCoord;
    } // getX ();

    double getY() {
        return yCoord;
    } // getY ();

    int getRadius() {
        return radius / 2;
    } // getRadius ();
    
    public Point getCenter() {
        return new Point(xCoord + getRadius(), yCoord + getRadius());
    } // getCenter ();
    
    @Override
    public boolean shouldDestory() {
        return yCoord >= 800;
    } // shouldDestory ();

    /**
     * Has a cool down for an Asteroid being hit by the same laser.
     * @return int the hardness of the Asteroid.
     */
    public int scoredHit() {
        if (coolDown == 0) {
            hardness--;
            colour = new Color(
                    (int) ((1.0 - (double) hardness / initialHardness) * 255),
                    0,
                    (int) (((double) hardness / initialHardness) * 255)
            );
            coolDown = 2;
        }
        return hardness;
    } // scoredHit ();

    /**
     * Drawable: draw, draws the Asteroid to the Graphics context.
     * @param g The graphics context.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval(xCoord, yCoord, radius, radius);

        if (coolDown != 0)
            coolDown--;

        this.updatePos();
        if (yCoord >= 800)
            Menu.setMenu(Menu.LOSE);
    } // draw (Graphics);

    /**
     * Checks if the Asteroid was hit by the laser.
     * @param p The point at which the line ends.
     * @return boolean: True if hit.
     */
    public boolean checkHit(Point p) {
        if (Helper.lineIntercetCircle(
                new Point(400, 800),
                p,
                getCenter(),
                getRadius()
        )) {
            if (scoredHit() == 0)
                return true;
        } // if(Asteroid hit);
        return false;
    } // checkHit (Point)
}// Asteroid;

