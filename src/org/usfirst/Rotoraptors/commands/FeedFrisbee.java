/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.indexer.AdvanceDown;

/**
 *
 * @author Daniel
 */
public class FeedFrisbee extends CommandBase {
    
    boolean finished = false;
    
    public FeedFrisbee() {
        // Use requires() here to declare subsystem dependencies
        requires(indexer);
        requires(screw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        screw.setShooterDist(Constants.Shooter.DIST_TO_FEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(indexer.getFeederOptical()) {
            Timer.delay(.1);
            AdvanceDown ad = new AdvanceDown();
            ad.start();
            finished = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        indexer.deactivate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        
    }
}
