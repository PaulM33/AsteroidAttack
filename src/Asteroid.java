
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
    final int initialHardness;
    int hardness;
    private Color colour;
    boolean flag;
    
    int coolDown;
    
    //Constructor
    public Asteroid() {
        this.xCoord = Helper.rand(50, 750);
        this.yCoord = 20;
        this.radius = Helper.rand(20, 50);
        this.velocity = Helper.rand(1, 5);
        this.initialHardness = Helper.rand(1, 4);
        this.hardness = initialHardness;
        this.colour = Color.BLUE;
        
        this.coolDown = 0;
        
        this.flag = false;
        
    }//Asteroid()
    
    //Second constructor for twin
    public Asteroid(Asteroid mom, int deltaX) {
       deltaX = mom.xCoord - mom.radius;
       this.xCoord = deltaX;
       this.yCoord = mom.yCoord;
       this.radius = mom.radius / 2;
       this.velocity = mom.velocity;
       this.initialHardness = mom.initialHardness;
       this.hardness = this.initialHardness;
       this.colour = Color.BLUE;
       this.flag = true;
       this.coolDown = 0;
        
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
    
    public int scoredHit() {        
        if(coolDown == 0) {
            hardness--;
            colour = new Color(
                    (int)((1.0 - (double)hardness/initialHardness) * 255),
                    0,
                    (int)(((double)hardness/initialHardness) * 255)
            );
            coolDown = 2;
        }
        
        return hardness;
        
    }//scoredHit()
    

    @Override
    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval(xCoord, yCoord, radius, radius);
        
        if(coolDown != 0) {
            coolDown--;
            
        }
        
        this.updatePos();
    } // draw (Graphics);
    
    public Point getCenter() {
        return new Point(xCoord + getRadius(), yCoord + getRadius());
    } // getCenter ();

    @Override
    public boolean shouldDestory() {
        return yCoord >= 800;
    } // shouldDestory ();
}// Asteroid;

