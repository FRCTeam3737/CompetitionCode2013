package org.usfirst.Rotoraptors;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
    
    /**********************   Chassis    **********************/
    
    // Declare Motor controllers
//    public static Talon leftMotor;
//    public static Talon rightMotor;
    public static Jaguar leftMotor;
    public static Jaguar rightMotor;

    // Declare RobotDrive object
    public static RobotDrive drive;

    // Declare Encoders
    public static Encoder leftEncoder;
    public static Encoder rightEncoder;

    // Declare PID controllers
    public static PIDController leftPID;
    public static PIDController rightPID;
    
    // Declare PID Constants
    public static double Kp = 0.0;
    public static double Ki = 0.0;
    public static double Kd = 0.0;    
    
    /**********************  Shooter  **********************/
    
    public static Talon frontShooterWheel;
    public static Talon rearShooterWheel;
    
    
    /**********************  Climber  **********************/
    
    
    /**********************  Loader   **********************/
    
    
    /**********************   Misc    **********************/
        
    // Declare Cameras
    public static AxisCamera cameraShooter;
    
    // Create instances of every component on the robot
    public static void init() {
//        leftMotor = new Talon(1);
//        rightMotor = new Talon(2);
        leftMotor = new Jaguar(1);
        rightMotor = new Jaguar(2);
//        frontShooterWheel = new Talon(3);
//        rearShooterWheel = new Talon (4);

        drive = new RobotDrive(leftMotor, rightMotor);    
        drive.setMaxOutput(1.0);
//        
//        leftEncoder = new Encoder(1, 2, false, CounterBase.EncodingType.k4X);
//        rightEncoder = new Encoder(3, 4, false, CounterBase.EncodingType.k4X);
//        
//        leftEncoder.reset();
//        leftEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
//        leftEncoder.setDistancePerPulse(RobotMap.Encoders.INCHES_PER_PULSE);
//        leftEncoder.start();      
//        
//        rightEncoder.reset();
//        rightEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
//        rightEncoder.setDistancePerPulse(RobotMap.Encoders.INCHES_PER_PULSE);
//        rightEncoder.start();    
//        
//        leftPID = new PIDController(Kp, Ki, Kd, leftEncoder, leftMotor);
//        rightPID = new PIDController(Kp, Ki, Kd, rightEncoder, rightMotor);
//        
//        cameraShooter = AxisCamera.getInstance(RobotMap.Cameras.AXISCAM_1);
//        cameraShooter.writeResolution(AxisCamera.ResolutionT.k320x240);
//        cameraShooter.writeMaxFPS(15);
//        cameraShooter.writeCompression(30);
        
//        LiveWindow.addActuator("Chassis", "frontLeftWheel", (Talon) leftMotor);
//        LiveWindow.addActuator("Chassis", "frontRightWheel", (Talon) rightMotor);
        LiveWindow.addActuator("Chassis", "frontLeftWheel", (Jaguar) leftMotor);
        LiveWindow.addActuator("Chassis", "frontRightWheel", (Jaguar) rightMotor);
         
//        LiveWindow.addSensor("Chassis", "leftEncoder", (Encoder) leftEncoder);
//        LiveWindow.addSensor("Chassis", "rightEncoder", (Encoder) rightEncoder);
        
//        LiveWindow.addActuator("Shooter", "frontWheel", (Talon) frontShooterWheel);
//        LiveWindow.addActuator("Shooter", "rearWheel", (Talon) rearShooterWheel);
    }   

    public static final class Encoders {
        // Encoder Port #s and values
        public static final int RD_ENC_PORT_A = 1;        //Digital IO
        public static final int RD_ENC_PORT_B = 2;        //Digital IO
        public static final int LD_ENC_PORT_A = 3;        //Digital IO
        public static final int LD_ENC_PORT_B = 4;        //Digital IO
        
        public static final int CODES_PER_REV = 250;      //Pulses per Encoder revolution
        public static final double INCHES_PER_PULSE = 0;  //Distance per Encoder pulse
    }

    public static final class Cameras {
        public static final String AXISCAM_1 = "10.37.37.11";   //IP address
        public static final String AXISCAM_2 = "10.37.37.12";   //IP address
    }

    // Set up a subclass for universal mathematical constants
    public static final class Constants {      
        
    }
}

    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
