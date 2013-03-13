/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.indexer.*;

/**
 *
 * @author Daniel
 */
public class Indexer extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    Relay liftRelay;
    DigitalInput prox; 
    DigitalInput topOpt;
    DigitalInput bottomOpt;
    DigitalInput shooterOpt;   
    DigitalInput feederOpt;
    
    public Indexer() {
        liftRelay = new Relay(RobotMap.Relays.INDEXER_RELAY);
        
        prox = new DigitalInput(RobotMap.Sensors.INDEXER_PROX);
        topOpt = new DigitalInput(RobotMap.Sensors.INDEXER_TOP_OPT);
        bottomOpt = new DigitalInput(RobotMap.Sensors.INDEXER_BOTTOM_OPT);
        shooterOpt = new DigitalInput(RobotMap.Sensors.INDEXER_SHOOTER_OPT);
        feederOpt = new DigitalInput(RobotMap.Sensors.INDEXER_FEEDER_OPT);
        
        LiveWindow.addActuator("Indexer", "liftRelay", (Relay) liftRelay);
                
        LiveWindow.addSensor("Indexer", "prox", (DigitalInput) prox);
        LiveWindow.addSensor("Indexer", "topOpt", (DigitalInput) topOpt);
        LiveWindow.addSensor("Indexer", "bottomOpt", (DigitalInput) bottomOpt);
        LiveWindow.addSensor("Indexer", "shooterOpt", (DigitalInput) shooterOpt);
        LiveWindow.addSensor("Indexer", "feederOpt", (DigitalInput) feederOpt);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothing());
    }
    
    public void runUp() {
        if(!getTopOptical()) {
            liftRelay.set(Relay.Value.kForward);
        } else {
            deactivate();
        }
    }
    
    public void runDown() {
        if(!getBottomOptical()) {
            liftRelay.set(Relay.Value.kReverse);
        } else {
            deactivate();
        }
    }
    
    public void deactivate() {
        liftRelay.set(Relay.Value.kOff);
    }
    
    public boolean getProxSensor() {
        return prox.get();
    }  
    
    public boolean getTopOptical() {
        return topOpt.get();
    }
    
    public boolean getBottomOptical() {
        return bottomOpt.get();
    }
    
    public boolean getShooterOptical() {
        return shooterOpt.get();
    }
    
    public boolean getFeederOptical() {
        return feederOpt.get();
    }   
}
