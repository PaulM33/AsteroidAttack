
import java.awt.Point;

/**
 * Program Name: Helper.java
 * Purpose: Hold helper methods for the Asteroid project
 * Author: Paul Magbor, Daniel Krauskopf
 * Date: 13-Aug-2016
 */
public class Helper {
    /**
     * Generates a random number within a range.
     * @param low The lower bound.
     * @param high The upper bound.
     * @return The random number.
     */
    public static int rand(int low, int high) {
        return (int)(Math.random() * (high - low + 1) + low);
    }// rand(int, int)
    
    /**
     * Increases a line by a given length.
     * @param p1 The starting point of the line.
     * @param p2 The Ending point of the line.
     * @param length The length to increase by.
     * @return The new ending point of the line.
     */
    public static Point increaseLine(Point p1, Point p2, int length) {
        // From: http://math.stackexchange.com/questions/352828/increase-length-of-line
        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;
        
        double cx = (x2 - x1) / ( Math.sqrt( Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0) ) );
        double cy = (y2 - y1) / ( Math.sqrt( Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0) ) );
        
        return new Point(p2.x + (int)(cx * length), p2.y + (int)(cy * length));
    } // increaseLine (Point, Point, int);
    
    /**
     * Detects if a line and a circle intersect.
     * @param p1 The starting point if the line.
     * @param p2 The ending point of the line.
     * @param p0 The centre of the circle.
     * @param radius The radius of the circle.
     * @return True if the line intersects the circle.
     */
    public static boolean lineIntercetCircle(Point p1, Point p2, Point p0, int radius) {
        double x0 = p0.x, y0 = p0.y;
        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;
        
        // Implemented using line to distance formula...
        double top = Math.abs(
                  (y2 - y1) * x0
                - (x2 - x1) * y0
                + x2 * y1
                - y2 * x1
        );
        double bottom = Math.sqrt(
                  Math.pow((y2 - y1), 2.0)
                + Math.pow((x2 - x1), 2.0)
        );
        
        return (top / bottom) <= radius;
    } // lineIntercetCircle (Point, Point, Point, int);
}// Helper;
