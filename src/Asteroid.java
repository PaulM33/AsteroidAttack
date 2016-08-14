
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Program Name: Asteroid.java
 * Purpose: The creation of asteroids for the game 
 * Author: Paul Magbor
 * Date: 13-Aug-2016
 */

public class Asteroid implements Drawable {
    //Class wide variables
    int xCoord;
    int yCoord;
    int velocity;
    int radius;
    int hardness;
    boolean flag;
    
    //Constructor
    public Asteroid(int x, int y, int radius, int velocity, int hardness) {
        this.xCoord = x;
        this.yCoord = y;
        this.radius = radius;
        this.velocity = velocity;
        this.hardness = hardness;
        
        this.flag = true;
        
    }//Asteroid(int, int, int, int, int)
    
    //Second constructor for twin
    public Asteroid(Asteroid mom, double deltaX) {
        Asteroid mini1 = new Asteroid(this.xCoord, 
                                      this.yCoord,
                                      this.radius,
                                      this.velocity, 
                                      this.hardness);
        
        Asteroid mini2 = new Asteroid(this.xCoord, 
                                      this.yCoord,
                                      this.radius,
                                      this.velocity, 
                                      this.hardness);
        
        mini1.xCoord = mom.xCoord - mom.radius;
        mini1.yCoord = mom.yCoord;
        mini1.radius = mom.radius / 2;
        mini1.velocity = mom.velocity;
        mini1.hardness = mom.hardness;
        mini1.flag = false;
        
        mini2.xCoord = mom.xCoord + mom.radius; 
        mini2.yCoord = mom.yCoord;
        mini2.radius = mom.radius / 2;
        mini2.velocity = mom.velocity;
        mini2.hardness = mom.hardness;
        mini2.flag = false;
        
    }//Asteroid(Asteroid, double)

    public boolean isTwin() {
        return Helper.rand(1, 100) <= 10;
        
    }//sTwin()
    
    public void updatePos() {
        yCoord = yCoord + velocity;
        
    }//updatePos()
    
    double getX() {
        return xCoord;
        
    }//getX()
    
    double getY() {
        return yCoord;
        
    }//getY()
    
    int getRadius() {
        return radius / 2;
        
    }//getRadius()
    
    public int sccoredHit() {
        --hardness; 
        return hardness;
        
    }//scoredHit()
    
    public boolean getTarget(double mouseX, double mouseY) {
        
        
        return false;
    }// getTarget (double, double);

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(xCoord, yCoord, radius, radius);
        
        this.updatePos();
    } // draw (Graphics);
    
    public Point getCenter() {
        return new Point(xCoord + getRadius(), yCoord + getRadius());
    } // getCenter ();
}// Asteroid;

