/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors;

/**
 *
 * @author Daniel
 */
public final class Constants {
    public static final class Sensors {
        // Calculated distance per revolution of encoders
        public static final double SCREW_DIST_PER_PULSE = 0.0004;
        public static final double DRIVE_DIST_PER_PULSE = 0.05236;
        
        // Voltage output increment of gyroscope per degree/second
        public static final double GYRO_V_PER_DEG_PER_S = .0007;
    }
    
    public static final class Shooter {
        public static final double POSITION_1 = 35;
        
        // Time to run injector bat (seconds)
        public static final double INJECTOR_TIME = .11;
        public static final double INJECTOR_WAIT_TIME = .05;
        
        // Speed to run shooter in normal conditions
        public static final double SHOOTER_TYP_SPEED = .9;
        public static double SHOOTER_IDLE = .08;
        
        /************ ALL OF THIS NEEDS TO BE DETERMINED VIA TESTING  *******************/
//        
//        // Counts of maximum and minimum allowable angle
//        public static final int MAX_SHOOTER_ANGLE = 55;
        public static final double MAX_SHOOTER_DIST = 8.7;
//        public static final int MIN_SHOOTER_ANGLE = 0;
        public static final double MIN_SHOOTER_DIST = .1;
//
//        // Angle and count to feed from station
//        public static final int ANGLE_TO_FEED = 1;
//        public static final int DIST_TO_FEED = 1;
//        
//        // Angle and count to setup for climbing
//        public static final int ANGLE_TO_CLIMB = 1;
//        public static final int DIST_TO_CLIMB = 1;
//
//        // Angle and count to shoot from left or right front pyramid corner
//        public static final int ANGLE_LR_FRONT_PYR_CRNR = 1;
//        public static final int DIST_LR_FRONT_PYR_CRNR = 1;
//        
//        // Angle and count to shoot from left or right rear pyramid corner
//        public static final int ANGLE_LR_REAR_PYR_CRNR = 1;
//        public static final int DIST_LR_REAR_PYR_CRNR = 1;        
//
//        // Angle and count to shoot from behind pyramid
//        public static final int ANGLE_BEHIND_PYR = 1;
//        public static final int DIST_BEHIND_PYR = 1;
//
//        // Angle and count to shoot from beside pyramid (teleop)
//        public static final int ANGLE_BESIDE_PYR = 1;
//        public static final int DIST_BESIDE_PYR = 1;                
    }
    
    public static final class Indexer {
        // Time to ignore input from prox before picking up the signal again (milliseconds)
        public static final double INDEXER_IGNORE_TIME = .18;
        
    }
}