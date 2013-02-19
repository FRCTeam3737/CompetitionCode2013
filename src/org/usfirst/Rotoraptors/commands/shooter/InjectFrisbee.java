/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.shooter;

import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class InjectFrisbee extends CommandBase {
    
    private boolean ready = false;
    private double m_timeout = .07;
    
    public InjectFrisbee() {
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        setTimeout(m_timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooter.spinUp();
        if (indexer.isFrisbeeInPosition() && indexer.isSlotAligned()) {
            ready = true;
        } else {
            ready = false;
            end();
        }        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (ready) {
            shooter.injectFrisbee();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.doNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.doNothing();
    }
}
