/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
        shooterCIM.set(speed);
    }
    
    public double getSpeed() {
        return shooterCIM.get();
    }
    
    public void doNothing() {        
        shooterCIM.set(0);
    }       
       
}
