/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.frisbeeInjector;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class InjectFrisbee extends CommandBase {
    
    private boolean ready = false;
    double timeStamp;
    double timeToRun = MathUtils.pow(Constants.Shooter.INJECTOR_TIME, -6);
        
    public InjectFrisbee() {
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timeStamp = Timer.getFPGATimestamp();
        
        if(indexer.getProxSensor()) {
            ready = true;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {      
        if(ready && ((Timer.getFPGATimestamp() - MathUtils.pow(timeStamp, -6))  <= timeToRun)) {
            injector.activate();
        } else {
            injector.retract();
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Timer.getFPGATimestamp() >= timeStamp + timeToRun ) && injector.getInjectorLimit());
    }

    // Called once after isFinished returns true
    protected void end() {
        injector.doNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
