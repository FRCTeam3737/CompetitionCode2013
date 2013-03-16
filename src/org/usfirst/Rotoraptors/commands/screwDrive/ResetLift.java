/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.screwDrive;

import org.usfirst.Rotoraptors.commands.CommandBase;
import org.usfirst.Rotoraptors.subsystems.ScrewDrive;

/**
 *
 * @author Daniel
 */
public class ResetLift extends CommandBase {
    
    public ResetLift() {
        // Use requires() here to declare subsystem dependencies
        requires(screw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       screw.control(-.3);    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return screw.getScrewLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
        screw.resetLiftEncoder();        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
