/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.Rotoraptors;


import org.usfirst.Rotoraptors.commands.CommandBase;
import org.usfirst.Rotoraptors.commands.Auton2;
import org.usfirst.Rotoraptors.commands.Auton0;
import org.usfirst.Rotoraptors.commands.Auton1;
import org.usfirst.Rotoraptors.utilities.Messager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RotoraptorsMain extends IterativeRobot {

    Messager msg;
    SendableChooser autoSwitcher, testSwitch;
    AxisCamera cameraFront;
    Command autonomousCommand, teleopCommand, testCommand;
    DriverStation driverstation;  

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {        
        driverstation = DriverStation.getInstance();  
        msg = new Messager();
        autoSwitcher = new SendableChooser();
        CommandBase.oi.updateDashboard();
        // Display scheduler data on SmartDashboard
        SmartDashboard.putData(Scheduler.getInstance());
        // Initialize camera
        cameraFront = AxisCamera.getInstance(RobotMap.Cameras.AXISCAM_1);
        cameraFront.writeResolution(AxisCamera.ResolutionT.k320x240);
        cameraFront.writeMaxFPS(18);
        // Create a switching autonomous
        autoSwitcher.addDefault("Auto 0", new Auton0());
        autoSwitcher.addObject("Auto 1", new Auton1());
        SmartDashboard.putData("Auto Switcher", autoSwitcher);
        
        msg.printLn("[robotInit] Initialization complete"); 

        // Initialize all subsystems
        CommandBase.init();
    }

    public void autonomousInit() {
         msg.printLn("[mode] Hybrid");     

        autonomousCommand = (Command) autoSwitcher.getSelected();        
        autonomousCommand.start();  
        msg.printLn("[status] " + autonomousCommand.getName() + " started");

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testInit() {
        msg.printLn("[mode] Dev");
        //CommandBase.oi.initLiveWindow();
        msg.printLn("[status] LiveWindow initialized");
        
        testSwitch = new SendableChooser();      
        testSwitch.addDefault("Autonomous 0", new Auton0());
        testSwitch.addObject("Autonomous 1", new Auton1());
        testSwitch.addObject("Autonomous 2", new Auton2());
        //testSwitch.addObject("Joystick Arcade (1)", new JoystickArcade());
        //testSwitch.addObject("Joystick Arcade (2)", new DualJoystickArcade());
        //testSwitch.addObject("Joystick Tank", new DualJoystickTank());
        SmartDashboard.putData("Drive Switch", testSwitch); 
                
        testCommand = (Command) testSwitch.getSelected();        
        testCommand.start();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void disabledInit() {
        autonomousCommand.cancel();
        teleopCommand.cancel();
        testCommand.cancel();
        msg.clearConsole();
        msg.printLn("[status] Robot Disabled");
        
    }
    
    public void disabledPeriodic() {
        
    }
}
