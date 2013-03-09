/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.shooter.*;

/**
 *
 * @author Daniel
 */
public class Shooter extends PIDSubsystem {

    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    private static final double Kf = 0.0;

    double shooterSpeed;
    double shooterRunTime;
    boolean runIndefinitely = false;
    double startTime;
    Timer timer;
    Jaguar shooterCIM;
    DigitalInput tachometer;
    
    // Initialize your subsystem here
    public Shooter() {
        super("Shooter", Kp, Ki, Kd, Kf);
        
        shooterCIM = new Jaguar(RobotMap.PWMControllers.SHOOTER_JAGUAR);
        shooterCIM.setSafetyEnabled(false);
        
        tachometer = new DigitalInput(RobotMap.Sensors.TACHOMETER);
        
        LiveWindow.addActuator("Shooter", "shooter", (Jaguar) shooterCIM);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothing());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}
