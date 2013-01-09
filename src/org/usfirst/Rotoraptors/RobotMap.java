package org.usfirst.Rotoraptors;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

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

    // Declare new RobotDrive using our Jaguars
    public static RobotDrive drive;

    // Declare Encoders
    public static Encoder leftEncoder;
    public static Encoder rightEncoder;

    // Declare new PID controllers
    public static PIDController leftPID;
    public static PIDController rightPID;
    
    public static void init() {
        frontLeftMotor = new Talon(1);
        rearLeftMotor = new Talon(2);
        frontRightMotor = new Talon(3);
        rearRightMotor = new Talon(4);
        
        drive = new RobotDrive(frontLeftMotor, rearLeftMotor,
                frontRightMotor, rearRightMotor);
        
        leftEncoder = new Encoder();
        rightEncoder = new Encoder();
        
        leftPID = new PIDController();
        leftPID = new PIDController();
        
    }
    
    
    
    public static final class Jaguars {

    }
    
    public static final class Talons {
        // PWM slot for Talon Motor Controllers
        
    }
    
    public static final class Relays {
        // Relay Channel #s
        
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
    
    public static final class Pots {
        // Potentiometers
        
    }
    
    public static final class Limits {
        // Limit Switches
        
    }
    
    // Optical/Ultrasonic
    public static final class Cameras {
        public static final String AXISCAM_1 = "10.37.37.11";   //IP address
        public static final String AXISCAM_2 = "10.37.37.12";   //IP address
    }

    /************************    Other Outputs    *******************/

    public static final class Compressor {
        // Compressor
    }
    
    public static final class Solenoids {
        // Solenoids
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
