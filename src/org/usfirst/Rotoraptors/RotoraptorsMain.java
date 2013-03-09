/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.Rotoraptors;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RotoraptorsMain extends IterativeRobot {

    private Command autonomousCommand;
    private SendableChooser autoSwitcher;
    public NetworkTable table;
    public AxisCamera camera;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Initializes all controllers
        CommandBase.init();
        // Create a switching autonomous mode
        camera.writeWhiteBalance(AxisCamera.WhiteBalanceT.hold);
        table = NetworkTable.getTable("datatable");
        autoSwitcher = new SendableChooser();
        autoSwitcher.addDefault("Auto 0", new Auton0());
        autoSwitcher.addObject("Auto 1", new Auton1());
        autoSwitcher.addObject("Auto 2", new Auton2());
        SmartDashboard.putData("Auto Switcher", autoSwitcher);
        SmartDashboard.putData(Scheduler.getInstance()); 
    }

    public void autonomousInit() {
        autonomousCommand = (Command) autoSwitcher.getSelected();    
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Watchdog.getInstance().feed();
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        Watchdog.getInstance().setEnabled(false);
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void disabledInit() {     
        Scheduler.getInstance().removeAll();     
    }
    
    public void disabledPeriodic() {
        updateDashboard();
    }
    
    public void updateDashboard() {
        
    }
    
    public void log(String out) {
        if (OI.DEV_MODE)
            System.out.println("RobotMain: " + out);
    }
}
