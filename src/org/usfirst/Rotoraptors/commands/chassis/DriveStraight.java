
package org.usfirst.Rotoraptors.commands.chassis;

import org.usfirst.Rotoraptors.commands.CommandBase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class DriveStraight extends CommandBase {
    
    private double m_timeout;
    private double m_speed;
    
    public DriveStraight(double speed, double timeout) {
        m_timeout = timeout;
        m_speed = speed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(m_timeout);
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        chassis.driveStraight(m_speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        chassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        chassis.stop();
    }
}
