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
    private final int m_angle;
    
    public SetAngle(int angle) {
        // Use requires() here to declare subsystem dependencies
        requires(screw);
        m_angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        screw.setShooterAngle(m_angle);
        screw.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        screw.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        screw.disable();
    }
}
