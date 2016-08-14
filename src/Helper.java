
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Program Name: Helper.java
 * Purpose: hold helper methods fro the steroid project
 * Author: Paul Magbor
 * Date: 13-Aug-2016
 */
public class Helper {
    public static int rand(int low, int high) {
        //Generate random number 
        return (int)(Math.random() * (high - low + 1) + low);
    }// rand(int, int)
    
    public static Point increaseLine(Point p1, Point p2, int length) {
        // From: http://math.stackexchange.com/questions/352828/increase-length-of-line
        double x1 = p1.x, y1 = p1.y;
        double x2 = p2.x, y2 = p2.y;
        
        double cx = (x2 - x1) / ( Math.sqrt( Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0) ) );
        double cy = (y2 - y1) / ( Math.sqrt( Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0) ) );
        
        return new Point(p2.x + (int)(cx * length), p2.y + (int)(cy * length));
    } // increaseLine (Point, Point, int);
    
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
