/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Daniel
 */
public class Compressor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Compressor compressor;

    public Compressor() {
        //compressor = new Compressor(1, 8);
        compressor.start();
    }

    public void start() {
        compressor.start();
    }

    public void stop() {
        compressor.stop();
    }
    
    public void reportPressure() {
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
