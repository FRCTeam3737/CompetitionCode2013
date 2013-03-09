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
public class AdvanceUp extends CommandBase {
    
    boolean reading1;
    boolean reading2;
    boolean reading3;
    boolean done;
    
    public AdvanceUp() {
        // Use requires() here to declare subsystem dependencies
        requires(indexer);
        setInterruptible(false);        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        reading1 = false;
        reading2 = false;
        reading3 = false;
        done = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        indexer.advanceUp();              
              
        if(reading1 && reading2) {
            if(indexer.getProxSensor()) {
                reading3 = true;
                done = true;
                isFinished();
            } else {
                reading1 = reading2 = reading3 = false;
            }
        }
        
        if(reading1) {
            if(indexer.getProxSensor()) {
                reading2 = true;
            } else {
                reading2 = false;
                reading1 = false;
            }
        }
        
        if(indexer.getProxSensor()) {
            reading1 = true;
        } else {
            reading1 = false;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
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
