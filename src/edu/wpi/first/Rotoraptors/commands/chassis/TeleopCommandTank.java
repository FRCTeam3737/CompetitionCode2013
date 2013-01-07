/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.Rotoraptors.commands.chassis;

import edu.wpi.first.Rotoraptors.commands.CommandBase;
import edu.wpi.first.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class TeleopCommandTank extends CommandBase {
    
    public TeleopCommandTank() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //chassis.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        chassis.joystickDrive(chassis.getTankLeftMotor(), chassis.getTankRightMotor());
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
