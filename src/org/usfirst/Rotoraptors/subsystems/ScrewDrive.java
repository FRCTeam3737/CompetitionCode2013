/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.screwDrive.OpControl;

/**
 *
 * @author Daniel
 */
public class ScrewDrive extends Subsystem {

    Jaguar screwCIM;

    Encoder screwEnc;
    DigitalInput screwLimit;
    
    double deadband = 2;
    
    // Initialize your subsystem here
    public ScrewDrive() {
        screwCIM = new Jaguar(RobotMap.PWMControllers.SCREW_JAGUAR);
        screwLimit = new DigitalInput(RobotMap.Sensors.SCREW_LIMIT);      
        
        screwEnc = new Encoder(
                RobotMap.Sensors.SCREW_ENC_PORT_A,
                RobotMap.Sensors.SCREW_ENC_PORT_B, 
                false, EncodingType.k4X);
        screwEnc.setDistancePerPulse(Constants.Sensors.SCREW_DIST_PER_PULSE);
        screwEnc.setReverseDirection(true);
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
              
    public void control(double input) {
        if((input > 0) && (getShooterDist() > Constants.Shooter.MAX_SHOOTER_DIST)) {
            screwCIM.set(0);
        } else if ((getShooterDist() < 1) && (input < 0) && (!getScrewLimit())) {
            screwCIM.set(input / 2);
        } if ((input <= 0) && getScrewLimit()) {
            screwCIM.set(0);
            screwEnc.reset();
        } else {
            screwCIM.set(input);
        }
    }
       
    public void setShooterDist(double dist) {
        if(dist >= screwEnc.getDistance()) {
            if (Math.abs(dist - screwEnc.getDistance()) > .6) {
                control(-.8);
            } else {
                control(0);
            }               
        } else if(dist < screwEnc.getDistance()) {
            if (Math.abs(dist - screwEnc.getDistance()) > .6) {
                control(.8);
            } else {
                screwCIM.set(0);
            }
        }            
    }
    
    public void setShooterAngle(double new_angle) {
        double dist = translateAngleToDist(new_angle);
        if(dist >= screwEnc.getDistance()) {
            if (Math.abs(dist - screwEnc.getDistance()) > .6) {
                control(-.7);
            } else {
                screwCIM.set(0);
            }               
        } else if(dist < screwEnc.getDistance()) {
            if (Math.abs(dist - screwEnc.getDistance()) > .6) {
                control(.7);
            } else {
                screwCIM.set(0);
            }
        }                 
    }
    
    public double getShooterAngle() {
        double dist = screwEnc.getDistance();        
        return translateDistToAngle(dist);
    }
    
    public double getShooterDist() {
        double dist = screwEnc.getDistance();
        return dist;
    }
    
    public boolean getScrewLimit() {
        return !screwLimit.get();
    }
    
    public void resetLiftEncoder() {
        screwEnc.reset();
    }
    
    public double translateDistToAngle(double dist) {
        double x = dist;
        double y = ((x + 2.35) / 6.18);
        return (double) y;
    }
    
    public double translateAngleToDist(double angle) {
        double x = angle;
        double y = ((6.18 * x) - 2.35);
        return (double) y;
    }
    
    public void deactivate() {
        screwCIM.set(0);
    }
}
