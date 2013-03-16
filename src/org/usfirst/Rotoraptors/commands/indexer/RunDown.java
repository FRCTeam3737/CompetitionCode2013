/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.indexer;

import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class RunDown extends CommandBase {
    
    public RunDown() {
        // Use requires() here to declare subsystem dependencies
        requires(indexer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!indexer.getBottomOptical()) {
            indexer.runDown();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return indexer.getBottomOptical();
    }

    // Called once after isFinished returns true
    protected void end() {
        indexer.deactivate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        indexer.deactivate();
    }
}
