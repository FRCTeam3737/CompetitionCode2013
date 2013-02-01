/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.Rotoraptors.OI;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.chassis.TeleopCommandArcade;
import org.usfirst.Rotoraptors.utilities.Messager;

/**
 * 
 * @author Daniel
 */
public class Chassis extends Subsystem {
    
    OI oi = new OI();
    Messager msg = new Messager();
    
    // Declare PID Constants
    private static final double Kp = RobotMap.Kp;
    private static final double Ki = RobotMap.Ki;
    private static final double Kd = RobotMap.Kd;    
        
    // Declare speed limits
    private static double speedLimit = 1.0;

    // Declare momentum compensation factor
    public static final double turningGain = 0;
    
    // Declare motor values pre momentum compensation
    public double leftMtr;
    public double rightMtr;
    
    // Declare motor values post momentum compensation
    public double leftMtrOut;
    public double rightMtrOut;
       
    // Declare Talons
//    private Talon leftMotor;
//    private Talon rightMotor;
    private Jaguar leftMotor;
    private Jaguar rightMotor;
    
    // Declare new RobotDrive using our Jaguars
    private RobotDrive drive;
       
    // Declare Encoders
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    
    // Declare new PID controllers
    private final PIDController leftPID;
    private final PIDController rightPID;  
    
    // Distance travelled since reset
    public double lDistance = leftEncoder.getDistance();   
    public double rDistance = rightEncoder.getDistance();
    
    // Current speed in inches per second
    public double lSpeed = leftEncoder.getRate();
    public double rSpeed = rightEncoder.getRate();
       
    // Gets the count
    public int lCount = leftEncoder.get();
    public int rCount = rightEncoder.get();
                  
    // Initialize your subsystem here
    public Chassis() {        
        leftMotor = RobotMap.leftMotor;
        rightMotor = RobotMap.rightMotor;

        drive = RobotMap.drive;
//        leftEncoder = RobotMap.leftEncoder;
//        rightEncoder = RobotMap.rightEncoder;
        
        // Configure Encoders
        configEncoder(leftEncoder);
        configEncoder(rightEncoder);
                              
        // Configure PID Controllers
        leftPID = new PIDController(Kp, Ki, Kd, leftEncoder, leftMotor);
        rightPID = new PIDController(Kp, Ki, Kd, rightEncoder, rightMotor);
        leftPID.setInputRange(0, 100);
        rightPID.setInputRange(0, 100);
        
        // Disable drive safety
        drive.setSafetyEnabled(false);
    }
        
    public void initDefaultCommand() {        
        // Set the default command for a subsystem here.
        setDefaultCommand(new TeleopCommandArcade());
    }
    
    // Procedure to configure an encoder for the Drivetrain
    private void configEncoder(Encoder m_enc) {
        m_enc.reset();
        m_enc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        m_enc.setDistancePerPulse(RobotMap.Encoders.INCHES_PER_PULSE);
        m_enc.start();       
    }
        
    /******************************************************************/

    // Retrieve values in Arcade drive configuration, apply algorithms
    public double getArcadeLeftMotor() {
        leftMtrOut = leftMtr + skim(rightMtr);
        return leftMtrOut * speedLimit;
    }

    public double getArcadeRightMotor() {
        rightMtrOut = rightMtr + skim(leftMtr); 
        return rightMtrOut * speedLimit;
    }
    
    // Retrieve values in Tank drive configuration
    public double getTankLeftMotor(){
        return oi.leftTank * speedLimit;
    }

    public double getTankRightMotor(){
        return oi.rightTank * speedLimit;
    }
    
    /*****************************************************************/
        
    // Drive with joysticks
    public void joystickDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

    /******************************************************************/
    
    // Limit the maximum speed, for precision
    public void limitSpeed(double newLimit) {
        speedLimit = newLimit;
    }
    
    /******************************************************************/
    
    // Drive straight at (speed) speed, 0.0 turn value
    public void driveStraight(double speed) { 
        drive.arcadeDrive(speed, 0.0);
    }
    
    // Turn left at (speed) speed, 0.1 turn value
    public void turnLeft(double speed) { 
        drive.arcadeDrive(0.1, speed);
    }
    
    // Turn right at (speed) speed, 0.1 turn value
    public void turnRight(double speed) {
        drive.arcadeDrive(0.1, -speed);
    }
    
    // Set all motors to 0
    public void stop(){
        drive.tankDrive(0.0, 0.0);
    }
    
    // Uses PID to drive distance
    public void driveDistance() {
        
    }
    
    // Uses PID to turn to an angle
    public void turnAngle() {
        
    }
       
        public static double skim(double input) {
    if (input > 1.0) {
        return -((input - 1.0) * turningGain);
    } else if (input < -1.0) {
        return -((input + 1.0) * turningGain);
    } return 0; 
    }
}
