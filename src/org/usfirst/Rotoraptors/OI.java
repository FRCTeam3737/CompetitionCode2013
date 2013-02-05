
package org.usfirst.Rotoraptors;

import org.usfirst.Rotoraptors.controls.hids.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.Rotoraptors.controls.hids.Attack3;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public static boolean CONSOLE_OUTPUT_ENABLED = true;
    
    // Initialize joysticks
    public static Attack3 leftJoystick;
    public static Attack3 rightJoystick;
    
    public OI() {
        leftJoystick = new Attack3(1);
        rightJoystick = new Attack3(2);
    }
    
    public static void updateDashboard(){
        SmartDashboard.putNumber("LeftY", leftJoystick.getY());
        SmartDashboard.putNumber("RightY", rightJoystick.getY());
        SmartDashboard.putNumber("LeftX", leftJoystick.getX());
        SmartDashboard.putNumber("RightX", rightJoystick.getX());
    }    

}

//// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());