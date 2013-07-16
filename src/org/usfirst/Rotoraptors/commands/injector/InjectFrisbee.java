/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.injector;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class InjectFrisbee extends CommandBase {
    
    // Timestamp of initialization
    private double startTime = 0.0;    
    // Time to run injector in milliseconds
    private double timeToRun = Constants.Shooter.INJECTOR_TIME;
    // Number of iterations to run for
    private double reverseSpeedCutoff = Constants.Shooter.INJECTOR_WAIT_TIME;
    
     public InjectFrisbee() {
        requires(injector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();    // Capture the starting time of the command
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {      
        if(((Timer.getFPGATimestamp() - startTime) < timeToRun)) {
            // If current time - starting time <= run time, push forward
            injector.activate();        
        } else {            
            if(!injector.getInjectorLimit()) {
                // Otherwise, if the limit switch isn't activated, retract
                if((Timer.getFPGATimestamp() - startTime) >= (timeToRun + reverseSpeedCutoff)) {
                    injector.retract(true);  // Retract at full speed until past "speed cutoff" timer
                } else {
                    injector.retract(false);  // If past that point, retract at half speed
                }               
            }            
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Timer.getFPGATimestamp() >= startTime + timeToRun ) && injector.getInjectorLimit());
        // Finished when full running time complete and limit switch is activated
    }

    // Called once after isFinished returns true
    protected void end() {
        injector.deactivate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
