/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.indexer;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class IndexDown extends CommandBase {
    
    public double startTime = 0.0;
    public boolean finished = false;  
    
    public IndexDown() {
        // Use requires() here to declare subsystem dependencies
        requires(indexer);
        setInterruptible(false);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {        
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if((Timer.getFPGATimestamp() - startTime) <= Constants.Indexer.INDEXER_IGNORE_TIME) {
            indexer.runDown();
        } else {
            indexer.runDown();
            if(indexer.getProxSensor() || indexer.getBottomOptical());
            indexer.deactivate();
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
