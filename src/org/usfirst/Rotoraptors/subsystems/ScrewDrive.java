/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.screwDrive.DoNothing;

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
        super("ScissorLift", Kp, Ki, Kd);
        
        screwCIM = new Jaguar(RobotMap.PWMControllers.SCREW_JAGUAR);
        screwLimit = new DigitalInput(RobotMap.Sensors.SCREW_LIMIT);      
        
        screwEnc = new Encoder(
                RobotMap.Sensors.SCREW_ENC_PORT_A,
                RobotMap.Sensors.SCREW_ENC_PORT_B, 
                false, EncodingType.k2X);
        screwEnc.setDistancePerPulse(1);
        screwEnc.start();
        screwEnc.reset();         
                
        LiveWindow.addActuator("Shooter", "scissorLift", (Jaguar) screwCIM);
        
        LiveWindow.addSensor("Shooter", "scissorEnc", (Encoder) screwEnc);  
        LiveWindow.addSensor("Shooter", "scissorEnc", (DigitalInput) screwLimit);

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
        return screwEnc.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        screwCIM.set(output);
    }
    
                   
    public void raiseShooter() {
        screwCIM.set(.1);
    }
        
    public void lowerShooter() {
        screwCIM.set(-.1);
    }
    
    public void setShooterAngle(double angle) {
        
    }    
    
    public double getShooterAngle() {
        int count = screwEnc.get();        
        return translateCountToAngle(count);
    }
    
    public boolean getScrewLim() {
        return screwLimit.get();
    }
    
    public void resetLift() {
        screwEnc.reset();
    }
    
    public double translateCountToAngle(int count) {
        int angle;
        
        return (int) 0;//angle;
    }
    
    public double translateAngleToCount(int angle) {
        return 0;
    }
}
