/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Daniel
 */
public class AirCompressor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Compressor compressor;
    SmartDashboard sd;

    public AirCompressor() {
        // Initializes the compressor
        compressor = new edu.wpi.first.wpilibj.Compressor(1, 8);
  }

    public void start() {
        compressor.start();
    }

    public void stop() {
        compressor.stop();
    }
    
    public boolean isPressureTripped() {
        return compressor.getPressureSwitchValue();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new RunCompressor());
    }
}
