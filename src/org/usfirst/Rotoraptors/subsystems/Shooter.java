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
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.shooter.*;

/**
 *
 * @author Daniel
 */
public class Shooter extends Subsystem {
    
    Indexer indexer = new Indexer();
    
    Jaguar shooterCIM;
    Relay feeder;
    
    DigitalInput feederLimit;

    public Shooter() {
          
        shooterCIM = new Jaguar(RobotMap.PWMControllers.SHOOTER_JAGUAR);
        feeder = new Relay(RobotMap.Relays.FEEDER_RELAY);
        
        feederLimit = new DigitalInput(RobotMap.Sensors.FEEDER_LIMIT) ;    

        LiveWindow.addActuator("Shooter", "shooter", (Jaguar) shooterCIM);
        LiveWindow.addActuator("Shooter", "injector", (Relay)feeder);
        
        LiveWindow.addSensor("Shooter", "feederLimit", (DigitalInput) feederLimit);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new SpinDown());
    }    

    public void injectFrisbee() {
         feeder.set(Relay.Value.kForward);
    }
    
    public void retractInjector() {
        while (!getFeederLim()) {
             feeder.set(Relay.Value.kReverse);
         }      
    }
    
    public void spinUp() {
        shooterCIM.set(.8);
    }
    
    public void spinDown() {
        shooterCIM.set(.2);
    }
    
    public void doNothing() {        
        shooterCIM.set(0);
    }       

    public boolean shooterIsReady() {
        if (getFeederLim() && indexer.isFrisbeeInPosition() && isSpunUp()) {
            return true;
        } else {
            return false;
        }
    }      
    
    private void configEncoder(Encoder m_enc) {
        m_enc.reset();
        m_enc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        m_enc.setDistancePerPulse(1);
        m_enc.start();       
    }
    
    public boolean getFeederLim() {
        return feederLimit.get();
    }
    
    public boolean isSpunUp() {
        if (shooterCIM.get() > .7) {
            return true;     
        } else {
            return false;
        }           
    }
    
    public boolean isInjectorRetracted() {
        return feederLimit.get();
    }
    
    public boolean isInjectorExtended() {
        return !feederLimit.get();
    }
    
}
