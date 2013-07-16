/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class Spin extends CommandBase {
    
    double shooterSpeed;
    double shooterRunTime;
    boolean runIndefinitely = false;
    double startTime;
    Timer timer;
    
    public Spin(double speed) {    // Runs the shooter for 7 seconds at a given speed
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        
        shooterSpeed = speed;
        shooterRunTime = 7;
    }
    
    public Spin(double speed, double time) {    // Runs the shooter for given time at given speed
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        
        shooterRunTime = time;
        shooterSpeed = speed;
    }
    
    public Spin(double speed, boolean indefinite) {   // Runs the shooter at given speed indefinitely
         // Use requires() here to declare subsystem dependencies
        requires(shooter);
        
        runIndefinitely = indefinite;
        shooterSpeed = speed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(shooterRunTime);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.setSpeed(shooterSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(runIndefinitely) {
            return false;
        } else {
            return isTimedOut();
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.setSpeed(0);     
    }
}
