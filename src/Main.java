
/**
 * 
 * Program Name: Main.java
 * @author Daniel Krauskopf
 * Purpose: The starting point of the program.
 * Date 13-Aug-2016
 */

/*
 * Note: We decided to use a JFrame instead of a JApplet for many reasons.
 *       1. JApplets have some more difficult coding issues.
 *          Cant be run from a main class
 *          Are already threaded and repaint() built in.
 *       2. JApplet's purpose is to be run in a browser, which can never be done
 *          by us because of security certificates. 
 *       3. Coding conventions: When making a game in Java the convention is to
 *          use a Conatiner and put it in a JFrame. This makes the game more
 *          portable and flexible.

 * Also a runnable .jar file is included in the project.
 * /dist/Asteroid_Attack.jar
 * 
 * Second Note: We programed this using Netbeans and Eclipse may or may not
 *              import it correctly.
 */
public class Main {
    public static void main(String[] args) {
        AsteroidAttack aa = new AsteroidAttack();
        aa.start();
    } // main(string[]);
} // Main;
