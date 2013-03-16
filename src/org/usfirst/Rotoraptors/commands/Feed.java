/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.Rotoraptors.commands;

import org.usfirst.Rotoraptors.Constants;

/**
 *
 * @author Daniel
 */
public class Feed extends CommandBase {
    
    boolean finished = false;
    
    public Feed() {
        // Use requires() here to declare subsystem dependencies
        requires(screw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        screw.setShooterAngle(Constants.Shooter.DIST_TO_FEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(indexer.getFeederOptical()) {
            finished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
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

