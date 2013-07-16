/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.screwDrive;

import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class OpControl extends CommandBase {
    
    // Class allows operator control over the screw drive via joystick or other input
    
    public OpControl() {
        // Use requires() here to declare subsystem dependencies
        requires(screw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        screw.control(oi.getYAxis() * -1);      
        // Gets the Y axis from joystick in OI and flips it, then plugs it into screw.control()                                          
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        screw.deactivate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        screw.deactivate();
    }
}
