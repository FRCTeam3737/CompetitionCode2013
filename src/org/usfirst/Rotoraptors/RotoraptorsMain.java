/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.Rotoraptors;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.commands.*;
import org.usfirst.Rotoraptors.commands.chassis.*;
import org.usfirst.Rotoraptors.subsystems.*;
import org.usfirst.Rotoraptors.utilities.Messager;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RotoraptorsMain extends IterativeRobot {

    public static DriverStation ds = DriverStation.getInstance();
    public static Messager msg = new Messager();
    private Command autonomousCommand, teleopCommand, testCommand;
    private SendableChooser autoSwitcher;
    private AxisCamera shooterCamera;   

    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Initializes all controllers
        RobotMap.init();
        CommandBase.init();
        // Display scheduler data on SmartDashboard
        SmartDashboard.putData(Scheduler.getInstance());        
        msg = new Messager();                
        // Initialize cameras
        //shooterCamera = RobotMap.cameraShooter;
        // Create a switching autonomous mode
        autoSwitcher = new SendableChooser();
        autoSwitcher.addDefault("Auto 0", new Auton0());
        autoSwitcher.addObject("Auto 1", new Auton1());
        autoSwitcher.addObject("Auto 2", new Auton2());
        SmartDashboard.putData("Auto Switcher", autoSwitcher);

        msg.printLn("[status] Initialized");       
    }

    public void autonomousInit() {
        msg.printLn("[mode]   Auton");     

        autonomousCommand = (Command) autoSwitcher.getSelected();        
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        OI.updateDashboard();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        msg.printLn("[mode]   Teleop");
        teleopCommand = new DriveWithJoysticks();
        autonomousCommand.cancel();
        teleopCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        OI.updateDashboard();
    }
    
    public void testInit() {
        msg.printLn("[mode]   Dev");
        msg.printLn("[status] LW Init");
        LiveWindow.setEnabled(true);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void disabledInit() {
        LiveWindow.setEnabled(false);
        
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        if (teleopCommand != null) {
            teleopCommand.cancel();
        }
        if (testCommand!= null) {
          testCommand.cancel();  
        }        
        
        msg.printLn("[status] Bot Disabled");
        
    }
    
    public void disabledPeriodic() {
        
    }
}
