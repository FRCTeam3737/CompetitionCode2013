package org.usfirst.Rotoraptors;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
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
 
    // Declare Motor controllers
    public static Talon frontLeftMotor;
    public static Talon rearLeftMotor;
    public static Talon frontRightMotor;
    public static Talon rearRightMotor;
    
    // Declare Compressor
    public static Compressor compressor;

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
        
    // Declare Cameras
    public static AxisCamera cameraFront;
    
    // Create instances of every component on the robot
    public static void init() {
        frontLeftMotor = new Talon(1);
        rearLeftMotor = new Talon(2);
        frontRightMotor = new Talon(3);
        rearRightMotor = new Talon(4);
        
        compressor = new Compressor(1, 8);
        
        drive = new RobotDrive(frontLeftMotor, rearLeftMotor,
                frontRightMotor, rearRightMotor);
        
        leftEncoder = new Encoder(1, 2, false, CounterBase.EncodingType.k4X);
        rightEncoder = new Encoder(3, 4, false, CounterBase.EncodingType.k4X);
        
        leftPID = new PIDController(Kp, Ki, Kd, leftEncoder, frontLeftMotor);
        rightPID = new PIDController(Kp, Ki, Kd, rightEncoder, frontRightMotor);
        
        cameraFront = AxisCamera.getInstance(RobotMap.Cameras.AXISCAM_1);
        cameraFront.writeResolution(AxisCamera.ResolutionT.k320x240);
        cameraFront.writeMaxFPS(15);
        cameraFront.writeCompression(30);
        
        LiveWindow.addActuator("Chassis", "frontLeftWheel", frontLeftMotor);
        LiveWindow.addActuator("Chassis", "frontRightWheel", frontRightMotor);
        LiveWindow.addActuator("Chassis", "backLeftWheel", rearLeftMotor);
        LiveWindow.addActuator("Chassis", "backRightWheel", rearRightMotor);
 
        LiveWindow.addSensor("Chassis", "leftEncoder", leftEncoder);
        LiveWindow.addSensor("Chassis", "rightEncoder", rightEncoder);
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
