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
        public static final int RFD_JAGUAR = 4;             //PWM
        public static final int LRD_JAGUAR = 3;             //PWM
        public static final int RRD_JAGUAR = 2;             //PWM
        public static final int SHOOTER_JAGUAR = 5;         //PWM
        public static final int SCREW_JAGUAR = 6;           //PWM
        public static final int INJECTOR_VICTOR = 7;        //PWM
    }
    
    public static final class CANJaguars {
        
    }
    
    public static final class Relays {
        public static final int INDEXER_RELAY = 2;          //Relay
    }
    
    public static final class Sensors {
        public static final int INDEXER_PROX = 1;           //Digital IO
        public static final int INDEXER_TOP_OPT = 2;        //Digital IO
        public static final int INDEXER_BOTTOM_OPT = 3;     //Digital IO
        public static final int INDEXER_SHOOTER_OPT = 4;    //Digital IO
        public static final int INDEXER_FEEDER_OPT = 5;     //Digtial IO
        public static final int FEEDER_LIMIT = 6;           //Digital IO
        public static final int SCREW_LIMIT = 7;            //Digital IO
        public static final int RD_ENC_PORT_A = 8;          //Digital IO
        public static final int RD_ENC_PORT_B = 9;          //Digital IO
//        public static final int LD_ENC_PORT_A = 3;        //Digital IO
//        public static final int LD_ENC_PORT_B = 4;        //Digital IO
        public static final int SCREW_ENC_PORT_A = 10;      //Digital IO
        public static final int SCREW_ENC_PORT_B = 11;      //Digital IO
        
        public static final int GYRO = 1;                   //Analog IO
    }
    
    public static final class Cameras {
        public static final String AXISCAM_1 = "10.37.37.11";   //IP address
        public static final String AXISCAM_2 = "10.37.37.12";   //IP address
    }
}