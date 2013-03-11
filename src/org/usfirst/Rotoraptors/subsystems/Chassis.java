/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.RobotMap;
import org.usfirst.Rotoraptors.commands.chassis.DriveWithJoysticks;
import org.usfirst.Rotoraptors.utilities.Utils;

/**
 * 
 * @author Daniel
 */
public class Chassis extends Subsystem {
      
    // Declare PID Constants for Encoders
    private static final double eKp = 0.0;
    private static final double eKi = 0.0;
    private static final double eKd = 0.0;
    
    // Declare PID Constants for Gyro
    private static final double gKp = 0.03;
    private static final double gKi = 0.0;
    private static final double gKd = 0.0;

    // Declare sensitivity
    public static double tSens = .15;
    
    // Declare booleans for PID controllers
    private boolean turnPIDenabled = false;
    private boolean drivePIDenabled = false;
       
    // Declare PWMControllers
    private Jaguar leftFrontMotor;
    private Jaguar rightFrontMotor;
    private Jaguar leftRearMotor;
    private Jaguar rightRearMotor;
    
    // Declare new RobotDrive using our PWMControllers
    private RobotDrive drive;
       
    // Declare Encoders
    private Encoder rightEncoder;
    
    // Declare Gyroscope
    private Gyro gyro;
    
    // Declare Ultrasonic
    private AnalogChannel sonic;
    
    // Declare PID 
    public PIDSource pidSourceGyro;
    public PIDSource pidSourceEncoder;
    public PIDOutput pidOutputGyro;
    public PIDOutput pidOutputEncoder;
    public PIDController pidGyro;
    public PIDController pidEncoder;
   
    // Initialize your subsystem here
    public Chassis() {   
        leftFrontMotor = new Jaguar(RobotMap.PWMControllers.LFD_JAGUAR);
        rightFrontMotor = new Jaguar(RobotMap.PWMControllers.RFD_JAGUAR);
        leftRearMotor = new Jaguar(RobotMap.PWMControllers.LRD_JAGUAR);
        rightRearMotor = new Jaguar(RobotMap.PWMControllers.RRD_JAGUAR);

        drive = new RobotDrive(
                leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor);
        drive.setMaxOutput(1.0);
        drive.setSafetyEnabled(false);
        
        gyro = new Gyro(RobotMap.Sensors.GYRO);
        gyro.setSensitivity(Constants.Sensors.GYRO_V_PER_DEG_PER_S);
               
        rightEncoder = new Encoder(
                RobotMap.Sensors.RD_ENC_PORT_A,
                RobotMap.Sensors.RD_ENC_PORT_B,
                false, CounterBase.EncodingType.k4X);   
                      
        // Configure Encoders
        configEncoder(rightEncoder);
        
        LiveWindow.addActuator("Chassis", "LF_Mtr", (Jaguar) leftFrontMotor);
        LiveWindow.addActuator("Chassis", "RF_Mtr", (Jaguar) rightFrontMotor);
        LiveWindow.addActuator("Chassis", "LR_Mtr", (Jaguar) leftRearMotor);
        LiveWindow.addActuator("Chassis", "LF_Mtr", (Jaguar) rightRearMotor);
         
        LiveWindow.addSensor("Chassis", "rightEncoder", (Encoder) rightEncoder);
        LiveWindow.addSensor("Chassis", "gyro", (Gyro) gyro);
    }    
        
    public void initDefaultCommand() {        
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    // Procedure to configure an encoder for the Drivetrain
    private void configEncoder(Encoder m_enc) {
        m_enc.reset();
        m_enc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        m_enc.setDistancePerPulse(Constants.Sensors.DRIVE_DIST_PER_PULSE);
        m_enc.start();       
    }
    
    private void initPID() {
        pidGyro = new PIDController(gKp, gKi, gKd, pidSourceGyro, pidOutputGyro);   
        pidEncoder = new PIDController(eKp, eKi, eKd, pidSourceEncoder, pidOutputEncoder);
    }   
    
    public void setSensitivity(double sensitivity){
        tSens = sensitivity;
    }
           
    /*****************************************************************/
        
    // Drive with joysticks
    public void joystickDrive(double left, double right, double speedLimit) {
        drive.tankDrive(left * speedLimit, right * speedLimit);
    }
    
    public void cheesyDrive(double throttleValue, double turnValue, boolean quickTurn) {
        double angular_power = 0.0;
        double overPower = 0.0;
        double sensitivity = tSens;
        double rPower = 0.0;
        double lPower = 0.0;

        if(quickTurn) {
            overPower = 1.0;
            sensitivity = 1.0;
            angular_power = turnValue;
        } else {
            overPower = 0.0;
            angular_power = Math.abs(throttleValue) * turnValue * sensitivity;
        }

        rPower = lPower = throttleValue;
        lPower += angular_power;
        rPower -= angular_power;

        if(lPower > 1.0) {
            rPower -= overPower * (lPower - 1.0);
            lPower = 1.0;
        }
        else if(rPower > 1.0) {
            lPower -= overPower * (rPower - 1.0);
            rPower = 1.0;
        }
        else if(lPower < -1.0) {
            rPower += overPower * (-1.0 - lPower);
            lPower = -1.0;
        }
        else if(rPower < -1.0) {
            lPower += overPower * (-1.0 - rPower);
            rPower = -1.0;
        }

        drive.setLeftRightMotorOutputs(lPower, rPower);
    }
    
    public void arcadeDrive(double throttleValue, double turnValue, double speedLimit) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        if (throttleValue > 1) {
            throttleValue = 1;
        }
        
        if(turnValue > 1) {
            turnValue = 1;
        }
        
        if (throttleValue > 0.0) {
            if (turnValue > 0.0) {
                leftMotorSpeed = throttleValue - turnValue;
                rightMotorSpeed = Math.max(throttleValue, turnValue);
            } else {
                leftMotorSpeed = Math.max(throttleValue, -turnValue);
                rightMotorSpeed = throttleValue + turnValue;
            }
        } else {
            if (turnValue > 0.0) {
                leftMotorSpeed = -Math.max(-throttleValue, turnValue);
                rightMotorSpeed = throttleValue + turnValue;
            } else {
                leftMotorSpeed = throttleValue - turnValue;
                rightMotorSpeed = -Math.max(-throttleValue, -turnValue);
            }
        }
        
        drive.setLeftRightMotorOutputs(
                leftMotorSpeed * speedLimit, rightMotorSpeed * speedLimit);
    }
    
//     public void arcadeDrive(double throttleValue, double turnValue, double speedLimit) {
//        double leftMtrOut;
//        double rightMtrOut;
//        double leftMtr;
//        double rightMtr;
//        leftMtr = throttleValue + turnValue;
//        rightMtr = throttleValue - turnValue;
//        leftMtrOut = leftMtr + skim(rightMtr);
//        rightMtrOut = rightMtr + skim(leftMtr); 
//        drive.tankDrive(leftMtrOut * speedLimit, rightMtrOut * speedLimit);
//    }
    

    /******************************************************************/
    
    // Drive straight at (speed) speed, 0.0 turn value
    public void driveStraight(double speed) { 
        drive.tankDrive(speed, speed);
    }
    
    // Turn left at (speed) speed, 0.1 turn value
    public void turnLeft(double speed) { 
        drive.tankDrive(0, speed);
    }
    
    // Turn right at (speed) speed, 0.1 turn value
    public void turnRight(double speed) {
        drive.tankDrive(speed, 0);
    }
    
    // Set all motors to 0
    public void stop(){
        drive.tankDrive(0.0, 0.0);
    }
    
    // Uses PID to drive distance
    public void driveToDistance(double setpoint) {
        
    }
    
    // Uses PID to turn to an angle
    public void turnToAngle(double setpoint) {
        
    }
    
    public double getDistance() {
        return rightEncoder.getDistance();
    }
    
     public double getCount() {
        return rightEncoder.get();
    }

}
