/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

/**
 *
 * @author Daniel
 */
public class DoNothing extends CommandBase {
    
    // This class does nothing.  It's a "kill command" for the robot that exits
    // current commands and essentially puts it in a default state.
    
    public DoNothing() {
        // Use requires() here to declare subsystem dependencies
       requires(chassis);
       requires(indexer);
       requires(shooter);
       requires(injector);
       requires(screw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        chassis.stop();
        indexer.deactivate();
        shooter.doNothing();
        injector.deactivate();
        screw.deactivate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
