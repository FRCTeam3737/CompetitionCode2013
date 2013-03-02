/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.utilities;

/**
 *
 * @author Daniel
 */
public class DetectedPoint {
    
    private double x;
    private double y;
    
    public DetectedPoint()
    {
        x = y = 0;
    }
    public DetectedPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }   
}