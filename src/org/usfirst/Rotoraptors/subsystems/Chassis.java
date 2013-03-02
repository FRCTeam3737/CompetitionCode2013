/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.subsystems;

import edu.wpi.first.wpilibj.Accelerometer;
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
    
    // Declare PID Constants for Ultrasonic sensor
    private static final double sKp = 0.0;
    private static final double sKi = 0.0;
    private static final double sKd = 0.0;
    
    // Declare PID Constants for Gyro
    private static final double gKp = 0.0;
    private static final double gKi = 0.0;
    private static final double gKd = 0.0;

    // Declare momentum compensation factor
    public static final double turningGain = .3;
       
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
    public PIDSource pidSourceSonic;
    public PIDSource pidSourceEncoder;
    public PIDOutput pidOutputGyro;
    public PIDOutput pidOutputSonic;
    public PIDOutput pidOutputEncoder;
    public PIDController pidGyro;
    public PIDController pidSonic;
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
        sonic = new AnalogChannel(RobotMap.Sensors.SONIC);
        rightEncoder = new Encoder(
                RobotMap.Sensors.RD_ENC_PORT_A,
                RobotMap.Sensors.RD_ENC_PORT_B,
                false, CounterBase.EncodingType.k4X);   
                      
        // Configure Encoders
        configEncoder(rightEncoder);
        
        initPID();

        LiveWindow.addActuator("Chassis", "LF_Mtr", (Jaguar) leftFrontMotor);
        LiveWindow.addActuator("Chassis", "RF_Mtr", (Jaguar) rightFrontMotor);
        LiveWindow.addActuator("Chassis", "LR_Mtr", (Jaguar) leftRearMotor);
        LiveWindow.addActuator("Chassis", "LF_Mtr", (Jaguar) rightRearMotor);
         
        LiveWindow.addSensor("Chassis", "rightEncoder", (Encoder) rightEncoder);
        LiveWindow.addSensor("Chassis", "gyro", (Gyro) gyro);
        LiveWindow.addSensor("Chassis", "ultrasonic", (AnalogChannel) sonic);
    }    
        
    public void initDefaultCommand() {        
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    // Procedure to configure an encoder for the Drivetrain
    private void configEncoder(Encoder m_enc) {
        m_enc.reset();
        m_enc.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        m_enc.setDistancePerPulse(.1);
        m_enc.start();       
    }
    
    private void initPID() {
        pidSourceGyro = new PIDSource() {
            public double pidGet() {  
                return gyro.getAngle();
            }
        };
        pidSourceSonic = new PIDSource() {
            public double pidGet() {
                return Utils.sonicGetDistance(sonic);
            }            
        };
        pidSourceEncoder = new PIDSource() {
            public double pidGet() {
                return rightEncoder.get();
            }
        };
        pidOutputGyro = new PIDOutput() {
            public void pidWrite(double output) {
                drive.tankDrive(output, output);
            }
        };
        pidOutputSonic = new PIDOutput() {
            public void pidWrite(double output) {
                drive.tankDrive(output, output);
            }
        };
        pidOutputEncoder = new PIDOutput() {
            public void pidWrite(double output) {
                drive.tankDrive(output, output);
            }
        };
        pidGyro = new PIDController(gKp, gKi, gKd, pidSourceGyro, pidOutputGyro);   
        pidSonic = new PIDController(sKp, sKi, sKd, pidSourceSonic, pidOutputSonic);
        pidEncoder = new PIDController(eKp, eKi, eKd, pidSourceEncoder, pidOutputEncoder);
    }
           
    /*****************************************************************/
        
    // Drive with joysticks
    public void joystickDrive(double left, double right, double speedLimit) {
        drive.tankDrive(left * speedLimit, right * speedLimit);
    }
    
    public void arcadeDrive(double throttleValue, double turnValue, double speedLimit) {
        double leftMtrOut;
        double rightMtrOut;
        double leftMtr;
        double rightMtr;
        leftMtr = throttleValue + turnValue;
        rightMtr = throttleValue - turnValue;
        leftMtrOut = leftMtr + skim(rightMtr);
        rightMtrOut = rightMtr + skim(leftMtr); 
        drive.tankDrive(leftMtrOut * speedLimit, rightMtrOut * speedLimit);
    }

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
          
    public static double skim(double input) {
        if (input > 1.0) {
            return -((input - 1.0) * turningGain);
        } else if (input < -1.0) {
            return -((input + 1.0) * turningGain);
        } return 0; 
    }
}
