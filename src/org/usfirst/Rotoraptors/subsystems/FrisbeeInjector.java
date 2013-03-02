/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.indexer.DoNothing;

/**
 *
 * @author Daniel
 */
public class FrisbeeInjector extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
            
    Indexer indexer = new Indexer();  
    
    Relay injector;    
    DigitalInput injectorLimit;
    
    public FrisbeeInjector() {       
        injector = new Relay(RobotMap.Relays.FEEDER_RELAY);
        
        injectorLimit = new DigitalInput(RobotMap.Sensors.FEEDER_LIMIT);    
        
        LiveWindow.addActuator("Shooter", "injector", (Relay)injector);
        
        LiveWindow.addSensor("Shooter", "feederLimit", (DigitalInput) injectorLimit);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothing());
    }
    
    public void doNothing() {
        injector.set(Relay.Value.kOff);
    }
    
     public void actuateInjector() {
         injector.set(Relay.Value.kForward);
    }
    
    public void retractInjector() {
         injector.set(Relay.Value.kReverse);
    }    
        
    public boolean getFeederLim() {
        return injectorLimit.get();
    }
    
    public boolean isInjectorRetracted() {
        return injectorLimit.get();
    }
    
    public boolean isInjectorExtended() {
        return !injectorLimit.get();
    }
    
    public boolean shooterIsReady() {
        if (getFeederLim() && indexer.isFrisbeeInPosition()) {
            return true;
        } else {
            return false;
        }
    }      
}
