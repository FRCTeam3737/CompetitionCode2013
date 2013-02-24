/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.utilities;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author Daniel
 */
public class Utils {
    public static double sonicGetDistance(AnalogChannel ai) {
        return ai.getVoltage() * 100;
    }
    
    public static double linearizeVictor884() {
        return 0;
    }
}
