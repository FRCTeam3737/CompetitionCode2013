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
public class DriveToDistance extends CommandBase {
    
    double setpoint;
    
    public DriveToDistance(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        requires(chassis);
        //this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //chassis.setSetpoint(setpoint);
        //chassis.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Math.abs(chassis.getPosition() - setpoint) < .02;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        //chassis.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //chassis.disable();
    }
}
