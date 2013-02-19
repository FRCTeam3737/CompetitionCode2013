/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.shooter;

import org.usfirst.Rotoraptors.commands.CommandBase;
import org.usfirst.Rotoraptors.subsystems.ScissorLift;

/**
 *
 * @author Daniel
 */
public class ResetScissorLift extends CommandBase {
    
    public ResetScissorLift() {
        // Use requires() here to declare subsystem dependencies
        requires(scissor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        while (!scissor.getScissorLim()) {
            scissor.lowerShooter();
        }        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return scissor.getScissorLim();
    }

    // Called once after isFinished returns true
    protected void end() {
        scissor.resetLift();        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
