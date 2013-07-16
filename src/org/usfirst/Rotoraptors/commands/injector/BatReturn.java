/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.injector;

import org.usfirst.Rotoraptors.commands.CommandBase;

/**
 *
 * @author Daniel
 */
public class BatReturn extends CommandBase {
    
    // Added as a way to return the bat if it got stuck in the forward position
    // should not be used in normal operation
    
    public BatReturn() {
        // Use requires() here to declare subsystem dependencies
        requires(injector);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!injector.getInjectorLimit()) {
            injector.retract(true);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return injector.getInjectorLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
        injector.deactivate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        injector.deactivate();
    }
}
