/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

/**
 *
 * @author Daniel
 */
public class AutoAim extends CommandBase {
    
    public final double middle = .01;
    public final double flat = 0.0;
    private double rotation;
    private double pitch;
    private double goal;
    private boolean on;
    
    public AutoAim() {
        // Use requires() here to declare subsystem dependencies
        requires(chassis);
        requires(shooter);
        requires(screw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //shooter.enable();
        screw.enable();
        //shooter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        screw.setShooterAngle(0);
        process(vision.analyze());
        
            if((goal == 0.0) || (goal == 1.0) || (goal == 2.0)){
                //shooter.setSetpoint(30);
                screw.setShooterAngle(pitch);
                chassis.turnToAngle(rotation);
            } else{
                //shooter.setSetpoint(0);
            }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        screw.disable();
        //shooter.disable();
        //chassis.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        screw.disable();
        //shooter.disable();
        //chassis.disable();
    }
    
     /**
     * Convert the data from an array into variables (rotation,pitch,goal.
     * @param analyze the array to be analyzed
     */
    private void process(double[] analyze) {
        rotation = analyze[1];
        pitch = analyze[2];
        goal = analyze[0];
    }
    
}
