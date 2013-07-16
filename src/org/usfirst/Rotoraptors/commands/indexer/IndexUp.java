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
public class IndexUp extends CommandBase {
    
    public double startTime = 0.0;
    public boolean finished = false; 
    
    public IndexUp() {
        // Use requires() here to declare subsystem dependencies
        requires(indexer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(indexer.getTopOptical()) {              // If top is blocked
                finished = true;                   // Set to finished
                indexer.deactivate();
            }          
        
        startTime = Timer.getFPGATimestamp();      // Get the starting time of the command
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        if(!finished) {             // If command isn't finished
            if((Timer.getFPGATimestamp() - startTime) <= Constants.Indexer.INDEXER_IGNORE_TIME) {
                // and the current time - start time ( = run time ) is less than the time needed
                // to get the shelf out of range of the prox sensor (thus preventing 
                // premature triggering & stopping)
            indexer.runUp();
        } else {            // Otherwise, if the ignore time has expired, pay attention to the prox sensor again
            indexer.runUp();
            if(indexer.getProxSensor()) {       // If prox sensor returns true, stop the indexer
                indexer.deactivate();
                finished = true;
            }       
        }         
        }        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {       
       return finished;    
    }

    // Called once after isFinished returns true
    protected void end() {
        indexer.deactivate();
        finished = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        indexer.deactivate();
 
    }
}
