/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.subsystems.*;

/**
 *
 * @author Daniel
 */
public class Dashboard {
    
    ScrewDrive screw;
    Shooter shooter;
    Indexer indexer;
    Injector injector;
    
    public Dashboard() {
         screw = new ScrewDrive();
         shooter = new Shooter();
         indexer = new Indexer();
         injector = new Injector();
    }
    
    public void updateDashboard() {
        
        SmartDashboard.putNumber("Shooter Angle", screw.getShooterAngle());
        
        if (OI.DEV_MODE) {
            
        } else {
            SmartDashboard.putBoolean("Indexer bottomOptical", indexer.getBottomOptical());
            SmartDashboard.putBoolean("Indexer topOptical", indexer.getTopOptical());
            SmartDashboard.putBoolean("Indexer feederOptical", indexer.getFeederOptical());
            SmartDashboard.putBoolean("Indexer shooterOptical", indexer.getShooterOptical());
            SmartDashboard.putBoolean("Indexer liftProximity", indexer.getProxSensor());
            
            //SmartDashboard.putNumber(null, value);
        }
        
    }
}
