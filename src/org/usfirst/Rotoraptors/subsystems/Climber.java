/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.Rotoraptors.commands.climber.DoNothing;

/**
 *
 * @author Daniel
 */
public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothing());
    }
    
    
}
