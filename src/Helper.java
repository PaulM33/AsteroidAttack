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
        int num = (int)(Math.random() * (high - low + 1) + low);
        
        return num;
        
    }//rand(int, int)

}//END Helper
