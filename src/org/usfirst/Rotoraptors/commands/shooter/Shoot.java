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
public class Shoot extends CommandBase {
    
    double shooterSpeed;
    double shooterRunTime;
    boolean runIndefinitely = false;
    double startTime;
    Timer timer;
    
    public Shoot(double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        
        shooterSpeed = speed;
        shooterRunTime = 7;
    }
    
    public Shoot(double speed, double time) {
        // Use requires() here to declare subsystem dependencies
        requires(shooter);
        
        shooterRunTime = time;
        shooterSpeed = speed;
    }
    
    public Shoot(double speed, boolean indefinite) {
         // Use requires() here to declare subsystem dependencies
        requires(shooter);
        
        runIndefinitely = indefinite;
        shooterSpeed = speed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
        shooter.runShooter(shooterSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.runShooter(shooterSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
        if(!runIndefinitely) {
            if((Timer.getFPGATimestamp()-startTime) > shooterRunTime) {
                return true;
            } else {
                return false;    
            }
        } else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        shooter.doNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.doNothing();
    }
}
