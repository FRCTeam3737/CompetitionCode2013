/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.shooter.*;

/**
 *
 * @author Daniel
 */
public class Shooter extends Subsystem {

    Jaguar shooterCIM;
    
    public Shooter() {          
        shooterCIM = new Jaguar(RobotMap.PWMControllers.SHOOTER_JAGUAR);
        shooterCIM.setSafetyEnabled(false);

        
        LiveWindow.addActuator("Shooter", "shooter", (Jaguar) shooterCIM);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothing());
    }    
    
    public void setSpeed(double speed) {
        class Power {
            double kZero = 0;
            double kIdle = Constants.Shooter.SHOOTER_IDLE;
            double kSpinUp = Constants.Shooter.SHOOTER_TYP_SPEED;
        }
        shooterCIM.set(speed);
    }
    
    public double getSpeed() {
        return shooterCIM.get();
    }
    
    public void doNothing() {        
        shooterCIM.set(0);
    }       
    
    private void configEncoder(Encoder m_enc) {
        m_enc.reset();
        m_enc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        m_enc.setDistancePerPulse(1);
        m_enc.start();       
    }
    
}
