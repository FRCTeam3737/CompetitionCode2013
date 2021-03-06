/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.chassis;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.OI;
import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class DriveWithJoysticks extends CommandBase {
    
    // This class drives the robot by utilizing joystick inputs
          
    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
         requires(chassis);
    }
    

    private double speedLimit;
    private boolean accuracyMode;

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // If Joystick button 2 is pushed, halve the maximum speed
        if (!OI.driverJoystick.getRawButton(2)) {
            speedLimit = 1.0;
            accuracyMode = false;
        } else {
            speedLimit = .5;
            accuracyMode = true;            
        }
        
        // If Joystick trigger is pulled, activate tank drive
        if (!OI.driverJoystick.getRawButton(1)) {
            chassis.arcadeDrive(OI.driverJoystick.getY(), OI.driverJoystick.getX(), speedLimit);
        } else {
            chassis.arcadeDrive(0, OI.driverJoystick.getX(), speedLimit);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
       
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      
    }
}
