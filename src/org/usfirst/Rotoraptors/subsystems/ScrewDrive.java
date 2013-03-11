/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.screwDrive.DoNothing;
import org.usfirst.Rotoraptors.commands.screwDrive.OpControl;

/**
 *
 * @author Daniel
 */
public class ScrewDrive extends PIDSubsystem {

    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    Jaguar screwCIM;

    Encoder screwEnc;
    DigitalInput screwLimit;
    
    // Initialize your subsystem here
    public ScrewDrive() {
        super("ScrewDrive", Kp, Ki, Kd);
        
        setPercentTolerance(2);
        setInputRange(Constants.Shooter.MAX_SHOOTER_ANGLE_COUNT,
                Constants.Shooter.MAX_SHOOTER_ANGLE_COUNT);
        screwCIM = new Jaguar(RobotMap.PWMControllers.SCREW_JAGUAR);
        screwLimit = new DigitalInput(RobotMap.Sensors.SCREW_LIMIT);      
        
        screwEnc = new Encoder(
                RobotMap.Sensors.SCREW_ENC_PORT_A,
                RobotMap.Sensors.SCREW_ENC_PORT_B, 
                false, EncodingType.k2X);
        screwEnc.setDistancePerPulse(Constants.Sensors.SCREW_DIST_PER_PULSE);
        screwEnc.start();
        screwEnc.reset();         
                
        LiveWindow.addActuator("Shooter", "screwDrive", (Jaguar) screwCIM);
        
        LiveWindow.addSensor("Shooter", "screwEnc", (Encoder) screwEnc);  
        LiveWindow.addSensor("Shooter", "screwLim", (DigitalInput) screwLimit);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OpControl());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return screwEnc.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        screwCIM.set(output);
    }    
                      
    public void manualControl(double input) {
        screwCIM.set(input);
    }
       
    public void setShooterCount(int count) {
        setSetpoint(count);
    }
    
    public void setShooterAngle(double angle) {
        int x = (int) angle;
        int count = (int) translateAngleToCount(x);
        setSetpoint(count);
    }
    
    public double getShooterAngle() {
        int count = screwEnc.get();        
        return translateCountToAngle(count);
    }
    
    public boolean getScrewLimit() {
        return screwLimit.get();
    }
    
    public void resetLiftEncoder() {
        screwEnc.reset();
    }
    
    public double translateCountToAngle(int count) {
        int x = count;
        double y = -1;
        //y = 2*(MathUtils.pow(x, 2));
        return (int) y;//angle;
    }
    
    public double translateAngleToCount(double angle) {
        double x = angle;
        int y = -1;
        //y = (int) (2*(MathUtils.pow(x, 2)));
        return (int) y;
    }
}
