/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.shooter.*;
import org.usfirst.Rotoraptors.utilities.hardware.OpticalTachometer;

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
    double outputScalingFactor;
    Timer timer;
    Jaguar shooterCIM;
    OpticalTachometer tachometer;
    
    // Initialize your subsystem here
    public Shooter() {
        super("Shooter", Kp, Ki, Kd, Kf);
        
        shooterCIM = new Jaguar(RobotMap.PWMControllers.SHOOTER_JAGUAR);
        shooterCIM.setSafetyEnabled(false);
        
        tachometer = new OpticalTachometer(RobotMap.Sensors.TACHOMETER);
        
        setAbsoluteTolerance(25);
        
        LiveWindow.addActuator("Shooter", "shooter", (Jaguar) shooterCIM); 
        LiveWindow.addActuator("Shooter", "PID Control", getPIDController());

        LiveWindow.addSensor("Shooter", "tachometer", tachometer);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
     /**
     * Sets the default command to be to wait 5 seconds, then spin the motor down.
     * This is called when all other commands using this subsystem have finished.
     */
    protected void initDefaultCommand() {
        CommandGroup timeout = new CommandGroup("Time out shooter");
        timeout.addSequential(new DoNothing(), 5);
        timeout.addSequential(new SpinDown());
        setDefaultCommand(timeout);
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return tachometer.getSpeedRpm();
    }
    
    /**
     * Uses the PID output to drive the motor
     * @param output the output to use
     */
    protected void usePIDOutput(double output) {
        shooterCIM.set(outputScalingFactor*output);
    }
    
    /**
     * Gets the current speed of the shooter wheel in RPM
     * @return the speed
     */
    public double getSpeedRpm() {
        return tachometer.getSpeedRpm();
    }

    /**
     * Gets the raw value of the shooter motor
     * @return the value
     */
    public double getMotorValRaw() {
        return shooterCIM.get();
    }
    
     /**
     * Sets the raw value of the shooter motor
     * @param val The new value
     */
    public void setMotorValRaw(double val) {
        shooterCIM.set(val);
    }
       
    public void setSpeed(double speed) {
        this.setSetpoint(speed);
    }
}
