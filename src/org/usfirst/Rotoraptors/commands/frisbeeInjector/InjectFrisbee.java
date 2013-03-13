/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.frisbeeInjector;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class InjectFrisbee extends CommandBase {
    
    // State machine variable
    private int state;    
    // Timestamp of initialization
    private double startTime = 0.0;    
    // Time to run injector in milliseconds
    private double timeToRun = Constants.Shooter.INJECTOR_TIME;
    // Number of iterations to run for
    boolean ready = false;
    
     public InjectFrisbee()
    {
        requires((Subsystem) injector);
    }
    
    public InjectFrisbee(int numIterations) {
        requires((Subsystem) injector);
    }
        
//    public InjectFrisbee() {
//        // Use requires() here to declare subsystem dependencies
//        requires(shooter);
//        setInterruptible(false);
//    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        
        if(indexer.getProxSensor()) {
            ready = true;
        }
        
        this.setInterruptible(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {      
        if(ready && ((Timer.getFPGATimestamp() - startTime)  <= timeToRun)) {
            injector.activate();
        } else {
            injector.retract();
        }        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Timer.getFPGATimestamp() >= startTime + timeToRun ) && injector.getInjectorLimit());
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
