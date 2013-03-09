package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
    public static Shoot shooter;
    public static Injector injector;
    public static ScrewDrive screw;
    public static Indexer indexer;
    public static Vision vision;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        chassis = new Chassis();
        shooter = new Shoot();
        injector = new Injector();
        screw = new ScrewDrive();
        indexer = new Indexer();
        vision = new Vision(RobotMap.Cameras.AXISCAM_1);
               
        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(chassis);

    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
