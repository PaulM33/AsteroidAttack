/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Program Name: Asteroid.java
 * Purpose: The creation of asteroids in the game 
 * Author: Paul Magbor
 * Date: 13-Aug-2016
 */
public class Asteroid {
    //Class wide variables
    int xCoord;
    int yCoord;
    int velocity;
    int radius;
    int hardness = (int)(Math.random() * (4 - 1 + 1) + 1);
    boolean flag;
    
    //Constructor
    public Asteroid(int x, int y, int radius, int velocity, int hardness) {
        this.xCoord = x;
        this.yCoord = y;
        this.radius = radius;
        this.velocity = velocity;
        this.hardness = hardness;
        
        this.flag = true;
        
    }//END Asteroid()
    
    public Asteroid(Asteroid mom, double deltaX) {
        
        
    }

    public boolean isTwin() {
        
        
    }//END isTwin()
    
    public void updatePos() {
        yCoord = yCoord + velocity;
        
    }//END updatePos()
    
    double getX() {
        return xCoord;
        
    }//END getX()
    
    double getY() {
        return yCoord;
        
    }//END getY()
    
    double getRadius() {
        return radius;
        
    }//END getRadius()
    
    public int sccoredHit() {
        hardness = hardness - 1;
        return hardness;
        
    }//END scoredHit()
    
    public boolean getTarget(double mouseX, double mouseY) {
        
    }//END getTarget
    
}//END Asteroid
