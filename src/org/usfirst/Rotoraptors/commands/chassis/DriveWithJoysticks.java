/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.chassis;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.OI;
import org.usfirst.Rotoraptors.RotoraptorsMain;
import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class DriveWithJoysticks extends CommandBase {
          
    public DriveWithJoysticks() {
        // Use requires() here to declare subsystem dependencies
         requires(chassis);
    }
    
    private String mode = (String) driveSwitcher.getSelected();
    private int driveMode;
    private double speedLimit;
    private boolean accuracyMode;

    // Called just before this Command runs the first time
    protected void initialize() {
        //chassis.enable();
        if ("splitDrive".equals(mode)) {
            driveMode = 1;
        } else if ("arcadeDrive".equals(mode)) {
            driveMode = 2;
        } else if ("tankDrive".equals(mode)) {
            driveMode = 3;
        } else {
            System.out.println("driveMode error");
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!OI.leftJoystick.south.get()) {
            speedLimit = 1.0;
            accuracyMode = false;
        } else {
            speedLimit = .5;
            accuracyMode = true;            
        }
        
        SmartDashboard.putBoolean("Speed Limit Enabled?", accuracyMode);
            
        switch (driveMode) {
            case 1: chassis.arcadeDrive(OI.leftJoystick.getY(), OI.rightJoystick.getX(), speedLimit);                    
                break;
            case 2: chassis.arcadeDrive(OI.rightJoystick.getY(), OI.rightJoystick.getX(), speedLimit);
                break;
            case 3: chassis.joystickDrive(OI.leftJoystick.getY(), OI.rightJoystick.getY(), speedLimit);
                break;
            default: chassis.joystickDrive(OI.leftJoystick.getY(), OI.rightJoystick.getY(), speedLimit);
                break;               
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        //chassis.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //chassis.disable();
  
    }
}
