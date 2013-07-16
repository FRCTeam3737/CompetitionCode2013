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
public class SetAngle extends CommandBase {
    private double m_angle;
    
    // Sets the lift to a specified angle
    
    public SetAngle(double angle) {
        // Use requires() here to declare subsystem dependencies
        requires(screw);
        m_angle = angle;
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {      
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        screw.setShooterAngle(m_angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return screw.onTarget();
        if(Math.abs(screw.getShooterAngle() - m_angle) < 4) {
            return true;
        } else {
            return false;
        }            
    }

    // Called once after isFinished returns true
    protected void end() {
        screw.deactivate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        
    }
}
