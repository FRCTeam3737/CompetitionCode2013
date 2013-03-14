package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.OI;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.subsystems.*;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Chassis chassis;
    public static Shooter shooter;
    public static Indexer indexer;
    public static Injector injector;
    public static ScrewDrive screw;
    
    public static Vision vision;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        chassis = new Chassis();
        shooter = new Shooter();
        indexer = new Indexer();
        injector = new Injector();
        screw = new ScrewDrive();
        
        vision = new Vision(RobotMap.Cameras.AXISCAM_1);
               
        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(chassis);
        SmartDashboard.putData(screw);
        SmartDashboard.putData(indexer);
        SmartDashboard.putData(injector);
        SmartDashboard.putData(shooter);

    }
    
      public static void updateDashboard() {
        
        SmartDashboard.putNumber("Shooter Angle", screw.getLiftAngle());
        
        if (OI.DEV_MODE) {
            
        } else {
            SmartDashboard.putBoolean("Indexer bottomOptical", indexer.getBottomOptical());
            SmartDashboard.putBoolean("Indexer topOptical", indexer.getTopOptical());
            SmartDashboard.putBoolean("Indexer feederOptical", indexer.getFeederOptical());
            SmartDashboard.putBoolean("Indexer frisbeeAligned", indexer.getShooterOptical());
            SmartDashboard.putBoolean("Indexer liftProximity", indexer.getProxSensor());
            
            SmartDashboard.putNumber("Shooter Dist", screw.getLiftDist());
            SmartDashboard.putNumber("Shooter Angle", screw.getLiftAngle());
            
            SmartDashboard.putNumber("Shooter RPM", shooter.getSpeedRpm());
            SmartDashboard.putNumber("Shooter Mtr value", shooter.getMotorValRaw());
            
            //SmartDashboard.putNumber(null, value);
        }        
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
