/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.chassis;

import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class ResetChassis extends CommandBase {
    
    public ResetChassis() {
        // Use requires() here to declare subsystem dependencies
        requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//        chassis.resetEncoder();
//        chassis.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
