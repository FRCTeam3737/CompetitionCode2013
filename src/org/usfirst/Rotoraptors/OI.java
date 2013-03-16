
package org.usfirst.Rotoraptors;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.Rotoraptors.commands.Climb;
import org.usfirst.Rotoraptors.commands.Feed;
import org.usfirst.Rotoraptors.commands.FeedSequence;
import org.usfirst.Rotoraptors.commands.Fire;
import org.usfirst.Rotoraptors.commands.indexer.IndexDown;
import org.usfirst.Rotoraptors.commands.indexer.IndexUp;
import org.usfirst.Rotoraptors.commands.screwDrive.ResetLift;
import org.usfirst.Rotoraptors.commands.shooter.Shoot;
import org.usfirst.Rotoraptors.controls.hids.Attack3;
import org.usfirst.Rotoraptors.controls.hids.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public static boolean DEV_MODE = false;
    
    // Initialize joysticks
    public static Attack3 driverJoystick;
    public static XboxController operatorJoystick;
    //public static eStopCCI cci;
    
    public Button X = new JoystickButton(operatorJoystick, XboxController.X_BUTTON);
    public Button Y = new JoystickButton(operatorJoystick, XboxController.Y_BUTTON);
    public Button A = new JoystickButton(operatorJoystick, XboxController.A_BUTTON);
    public Button B = new JoystickButton(operatorJoystick, XboxController.B_BUTTON);
    public Button lBumper = new JoystickButton(operatorJoystick, XboxController.LEFT_BUMPER);
    public Button rBumper = new JoystickButton(operatorJoystick, XboxController.RIGHT_BUMPER);
    public Button start = new JoystickButton(operatorJoystick, XboxController.START_BUTTON);
    public Button back = new JoystickButton(operatorJoystick, XboxController.BACK_BUTTON);
    public Button lStick = new JoystickButton(operatorJoystick, XboxController.LEFT_STICK);
    public Button rStick = new JoystickButton(operatorJoystick, XboxController.RIGHT_STICK);
    
    public Button north = new JoystickButton(driverJoystick, Attack3.BUTTON_NORTH);
    public Button east = new JoystickButton(driverJoystick, Attack3.BUTTON_EAST);
    public Button west = new JoystickButton(driverJoystick, Attack3.BUTTON_WEST);
    public Button base_ene = new JoystickButton(driverJoystick, Attack3.BUTTON_BASE_ENE);
    public Button base_ese = new JoystickButton(driverJoystick, Attack3.BUTTON_BASE_ESE);
    public Button base_sse = new JoystickButton(driverJoystick, Attack3.BUTTON_BASE_SSE);
    public Button base_ssw = new JoystickButton(driverJoystick, Attack3.BUTTON_BASE_SSW);
    public Button base_wnw = new JoystickButton(driverJoystick, Attack3.BUTTON_BASE_WNW);
    public Button base_wsw = new JoystickButton(driverJoystick, Attack3.BUTTON_BASE_WSW);
    
    public OI() {
        driverJoystick = new Attack3(1);
        operatorJoystick = new XboxController(2);
           
        east.whenPressed(new ResetLift());
        west.whenPressed(new Climb());
        north.whenPressed(new Fire());
        
        base_ese.whenPressed(new IndexUp());
        base_ene.whenPressed(new IndexDown());
        base_wnw.whenPressed(new FeedSequence());
        base_ssw.whenPressed(new ResetLift());        
        
        A.whenPressed(new Fire());
        B.whenPressed(new FeedSequence());
        
        lBumper.whenPressed(new Climb());
        rBumper.whenPressed(new ResetLift());      
    }
    
    public Attack3 getDriverJoystick() {
        return driverJoystick;
    }
        
    public XboxController getOperatorJoystick() {
        return operatorJoystick;
    }
    
//    public eStopCCI getCCI() {
//        return cci;
//    }
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