/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.injector.DoNothing;

/**
 *
 * @author Daniel
 */
public class Injector extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
            
    Victor injector;    
    DigitalInput injectorLimit;
    
    public Injector() {       
        injector = new Victor(RobotMap.PWMControllers.INJECTOR_VICTOR);        
        injectorLimit = new DigitalInput(RobotMap.Sensors.FEEDER_LIMIT);    
        
        LiveWindow.addActuator("Shooter", "injector", (Victor) injector);        
        LiveWindow.addSensor("Shooter", "feederLimit", (DigitalInput) injectorLimit);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DoNothing());
    }
    
     public void activate() {
         injector.set(1);
    }
     
     public void deactivate() {
         injector.set(0);
     }
    
    public void retract(boolean halfSpeed) {
        if(!getInjectorLimit()) {      // If injector limit isn't triggered, proceed
            if(!halfSpeed) {           // If not set to half speed
                injector.set(-1);           // Full speed backward
            } else {
                injector.set(-.25);         // Half speed backward (non-linear)
            }            
    }    
    }
        
    public boolean getInjectorLimit() {
        return !injectorLimit.get();
    }
}
