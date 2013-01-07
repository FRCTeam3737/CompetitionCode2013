package edu.wpi.first.Rotoraptors;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
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
