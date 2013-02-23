/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.scissorLift.DoNothing;

/**
 *
 * @author Daniel
 */
public class ScissorLift extends PIDSubsystem {

    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    Jaguar scissorCIM;

    Encoder scissorEnc;
    DigitalInput scissorLimit;
    
    // Initialize your subsystem here
    public ScissorLift() {
        super("ScissorLift", Kp, Ki, Kd);
        
        scissorCIM = new Jaguar(RobotMap.PWMControllers.SCISSOR_JAGUAR);
        scissorLimit = new DigitalInput(RobotMap.Sensors.SCISSOR_LIMIT);      
        
        scissorEnc = new Encoder(
                RobotMap.Sensors.SCISSOR_ENC_PORT_A,
                RobotMap.Sensors.SCISSOR_ENC_PORT_B);
        scissorEnc.setDistancePerPulse(.1);
        scissorEnc.start();
        scissorEnc.reset();         
                
        LiveWindow.addActuator("Shooter", "scissorLift", (Jaguar) scissorCIM);
        
        LiveWindow.addSensor("Shooter", "scissorEnc", (Encoder) scissorEnc);  
        LiveWindow.addSensor("Shooter", "scissorEnc", (DigitalInput) scissorLimit);

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
        return scissorEnc.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        scissorCIM.set(output);
    }
    
                   
    public void raiseShooter() {
        scissorCIM.set(.1);
    }
        
    public void lowerShooter() {
        scissorCIM.set(-.1);
    }
    
    public void setShooterAngle(double angle) {
        
    }    
    
    public double getShooterAngle() {
        int count = scissorEnc.get();        
        return translateCountToAngle(count);
    }
    
    public boolean getScissorLim() {
        return scissorLimit.get();
    }
    
    public void resetLift() {
        scissorEnc.reset();
    }
    
    public double translateCountToAngle(int count) {
        int angle;
        
        return (int) 0;//angle;
    }
    
    public double translateAngleToCount(int angle) {
        return 0;
    }
}
