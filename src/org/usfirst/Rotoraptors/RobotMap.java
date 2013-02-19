package org.usfirst.Rotoraptors;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {

    public static final class PWMControllers {
        public static final int LFD_JAGUAR = 1;             //PWM
        public static final int RFD_JAGUAR = 2;             //PWM
        public static final int LRD_JAGUAR = 3;             //PWM
        public static final int RRD_JAGUAR = 4;             //PWM
        public static final int SHOOTER_JAGUAR = 5;         //PWM
        public static final int SCISSOR_JAGUAR = 6;         //PWM
    }
    
    public static final class CANJaguars {
        
    }
    
    public static final class Relays {
        public static final int FEEDER_RELAY = 1;           //Relay
        public static final int INDEXER_RELAY = 2;          //Relay
        public static final int SCOOP_RELAY = 3;            //Relay
    }
    
    public static final class Sensors {
        public static final int INDEXER_PROX = 1;           //Digital IO
        public static final int INDEXER_TOP_OPT = 2;        //Digital IO
        public static final int INDEXER_BOTTOM_OPT = 3;     //Digital IO
        public static final int INDEXER_SHOOTER_OPT = 4;    //Digital IO
        public static final int FEEDER_LIMIT = 5;           //Digital IO
        public static final int SCISSOR_LIMIT = 6;          //Digital IO
        public static final int RD_ENC_PORT_A = 7;          //Digital IO
        public static final int RD_ENC_PORT_B = 8;          //Digital IO
//        public static final int LD_ENC_PORT_A = 3;          //Digital IO
//        public static final int LD_ENC_PORT_B = 4;          //Digital IO
        public static final int SCISSOR_ENC_PORT_A = 9;     //Digital IO
        public static final int SCISSOR_ENC_PORT_B = 10;    //Digital IO
        
        public static final int SONIC = 4;                  //Analog IO
        public static final int GYRO = 8;                   //Analog IO
    }
    
    public static final class Cameras {
        public static final String AXISCAM_1 = "10.37.37.11";   //IP address
        public static final String AXISCAM_2 = "10.37.37.12";   //IP address
    }
    
    public static final class ShooterConstants {
        // Counts of maximum and minimum allowable angle
        public static final int MAX_SHOOTER_ANGLE = 1;
        public static final int MAX_SHOOTER_ANGLE_COUNT = 1;
        public static final int MIN_SHOOTER_ANGLE = 0;
        public static final int MIN_SHOOTER_ANGLE_COUNT = 0;
        
        // Angle and count to feed from station
        public static final int ANGLE_TO_FEED = 1;
        public static final int COUNT_TO_FEED = 1;
        
        // Angle and count to shoot from left or right pyramid corner
        public static final int ANGLE_POS_1 = 1;
        public static final int COUNT_POS_1 = 1;
        
        // Angle and count to shoot from behind pyramid
        public static final int ANGLE_POS_2 = 1;
        public static final int COUNT_POS_2 = 1;
        
        // Angle and count to shoot from beside pyramid (teleop)
        public static final int ANGLE_POS_3 = 1;
        public static final int COUNT_POS_3 = 1;                
    }
}