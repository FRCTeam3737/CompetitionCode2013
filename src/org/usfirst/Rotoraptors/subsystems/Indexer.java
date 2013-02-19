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
    
    public Indexer() {
        liftRelay = new Relay(RobotMap.Relays.INDEXER_RELAY);
        prox = new DigitalInput(RobotMap.Sensors.INDEXER_PROX);
        topOpt = new DigitalInput(RobotMap.Sensors.INDEXER_TOP_OPT);
        bottomOpt = new DigitalInput(RobotMap.Sensors.INDEXER_BOTTOM_OPT);
        shooterOpt = new DigitalInput(RobotMap.Sensors.INDEXER_SHOOTER_OPT);
                
        LiveWindow.addActuator("Indexer", "liftRelay", liftRelay);
                
        LiveWindow.addSensor("Indexer", "prox", (DigitalInput) prox);
        LiveWindow.addSensor("Indexer", "topOpt", topOpt);
        LiveWindow.addSensor("Indexer", "bottomOpt", bottomOpt);
        LiveWindow.addSensor("Indexer", "shooterOpt", shooterOpt);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothing());
    }
    
    public void runUp() {
        if(!getTopOptical() && !getBottomOptical()) {
            liftRelay.set(Relay.Value.kForward);
        } else {
            deactivate();
        }
    }
    
    public void runDown() {
        if(!getTopOptical() && !getBottomOptical()) {
            liftRelay.set(Relay.Value.kReverse);
        } else {
            deactivate();
        }
    }
    
    public void deactivate() {
        liftRelay.set(Relay.Value.kOff);
    }
    
    public void advanceUp() {
        while(!isSlotAligned()) {
            runUp();
        }
    }
    
    public void advanceDown() {
        while(!isSlotAligned()) {
            runDown();
        }
    }
    
    public void advanceUpX(int slots) {
        
    }
    
    public void advanceDownX(int slots) {
        
    }
    
    public void advanceUpToNext() {
        
    }
    
    public void advanceDownToNext() {
        
    }
       
    public void goToSlot(int slot) {
        
    }
    
    public boolean isFrisbeeInPosition() {
        return shooterOpt.get();
    }
    
    public boolean isSlotAligned() {
        return prox.get();
    }  
    
    public boolean getTopOptical() {
        return topOpt.get();
    }
    
    public boolean getBottomOptical() {
        return bottomOpt.get();
    }
}
