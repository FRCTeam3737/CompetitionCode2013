/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.Rotoraptors.subsystems;

import edu.wpi.first.Rotoraptors.OI;
import edu.wpi.first.Rotoraptors.RobotMap;
import edu.wpi.first.Rotoraptors.commands.chassis.TeleopCommandArcade;
import edu.wpi.first.Rotoraptors.utilities.Messager;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Daniel
 */
public class Chassis extends Subsystem {
    
    OI oi = new OI();
    SmartDashboard sd = new SmartDashboard();
    Messager msg = new Messager();
    
    // Declare PID Constants
    private static final double Kp = 0.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;    
    
    // Declare feed-forward term
    private static final double Kf = 0.0;
    
    // Declare speed limits
    private double speedLimit = 1.0;

    // Declare momentum compensation factor
    public static final double turningGain = 0;
    
    // Declare motor values pre momentum compensation
    public double leftMtr;
    public double rightMtr;
    
    // Declare motor values post momentum compensation
    public double leftMtrOut;
    public double rightMtrOut;
       
    // Declare Talons
    private Talon frontLeftMotor;
    private Talon rearLeftMotor;
    private Talon frontRightMotor;
    private Talon rearRightMotor;
              
    // Declare new RobotDrive using our Jaguars
    private RobotDrive drive;
       
    // Declare Encoders
    public Encoder leftEncoder;
    public Encoder rightEncoder;
    
    // Declare new PID controllers
    public final PIDController leftPID;
    public final PIDController rightPID;
        
    // Distance travelled since reset
    double lDistance = leftEncoder.getDistance();   
    double rDistance = rightEncoder.getDistance();
    
    // Current speed in inches per second
    double lSpeed = leftEncoder.getRate();
    double rSpeed = rightEncoder.getRate();
       
    // Gets the count
    int lCount = leftEncoder.get();
    int rCount = rightEncoder.get();
                  
    // Initialize your subsystem here
    public Chassis() {
        
        drive = new RobotDrive(
                frontLeftMotor, rearLeftMotor, 
                frontRightMotor, rearRightMotor);
        
        // Initialize Encoders
        leftEncoder = new Encoder(RobotMap.Encoders.LD_ENC_PORT_A, 
                RobotMap.Encoders.LD_ENC_PORT_B, false, CounterBase.EncodingType.k4X);
        rightEncoder = new Encoder(RobotMap.Encoders.RD_ENC_PORT_A, 
                RobotMap.Encoders.RD_ENC_PORT_B, false, CounterBase.EncodingType.k4X);
        
        // Configure Encoders
        configEncoder(leftEncoder);
        configEncoder(rightEncoder);
                              
        leftPID = new PIDController(Kp, Ki, Kd, leftEncoder, frontLeftMotor);
        rightPID = new PIDController(Kp, Ki, Kd, rightEncoder, frontRightMotor);
        leftPID.setInputRange(0, 100);
        rightPID.setInputRange(0, 100);
              
        leftMtr = oi.throttle + oi.turn;
        rightMtr = oi.throttle - oi.turn;
    }
    
    public void initDefaultCommand() {        
        // Set the default command for a subsystem here.
        setDefaultCommand(new TeleopCommandArcade());
    }
    
    // Procedure to configure an encoder for the Drivetrain
    private void configEncoder(Encoder m_enc) {
        m_enc.setDistancePerPulse(RobotMap.Encoders.INCHES_PER_PULSE);
        m_enc.start();
        m_enc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    }
        
    /******************************************************************/

    // Retrieve values in Arcade drive configuration, apply algorithms
    public double getArcadeLeftMotor() {
        return leftMtr + skim(rightMtr);
    }

    public double getArcadeRightMotor() {
        return rightMtr + skim(leftMtr);            
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
    
    // Shift gear up
    public void shiftUp() {
        
    }
    
    // Shift gear down
    public void shiftDown() {
        
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
