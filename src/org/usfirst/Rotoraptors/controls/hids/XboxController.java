/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.controls.hids;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

/**
 *
 * @author Daniel
 */

public class XboxController extends Joystick {
    
    private Joystick m_pad;
    
     //Axes
    public static final int LEFT_XAXIS = 1;
    public static final int LEFT_YAXIS = 2;
    public static final int RIGHT_XAXIS = 4;
    public static final int RIGHT_YAXIS = 5;
    public static final int TRIGGERS = 3;
    public static final int DPAD_LR = 6;
    //Buttons
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int BACK_BUTTON = 7;
    public static final int START_BUTTON = 8;
    public static final int LEFT_STICK = 9;
    public static final int RIGHT_STICK = 10;
    
    // Creates buttons
//    public Button X = new JoystickButton(m_pad, X_BUTTON);
//    public Button Y = new JoystickButton(m_pad, Y_BUTTON);
//    public Button A = new JoystickButton(m_pad, A_BUTTON);
//    public Button B = new JoystickButton(m_pad, B_BUTTON);
//    public Button lBumper = new JoystickButton(m_pad, LEFT_BUMPER);
//    public Button rBumper = new JoystickButton(m_pad, RIGHT_BUMPER);
//    public Button start = new JoystickButton(m_pad, START_BUTTON);
//    public Button back = new JoystickButton(m_pad, BACK_BUTTON);
//    public Button lStick = new JoystickButton(m_pad, LEFT_STICK);
//    public Button rStick = new JoystickButton(m_pad, RIGHT_STICK);

    /**
     * Constructor creates an object to interface with an Xbox 360 controller
     *
     * @param	port	The port on the drivers station that the controller is
     * connected to
     * @return
     */
    public XboxController(int port) {
        super(port);
        m_pad = new Joystick(port);
    }

    /**
     * Get the value of X of the left joystick. The right side of the axis is
     * positive.
     *
     * @param
     * @return The value of the axis from -1 to 1.
     */
    public double getLeftX() {
        return applyDeadband(LEFT_XAXIS);
    }

    /**
     * Get the value of Y of the left joystick. The upward part of the axis is
     * positive.
     *
     * @param
     * @return The value of the axis from -1 to 1.
     */
    public double getLeftY() {
        return (-1 * applyDeadband(LEFT_YAXIS));
        //Multiply output by -1 because GetAxis() returns the axis backwards.
    }

    /**
     * Get the value of X of the right joystick. The right side of the axis is
     * positive.
     *
     * @param
     * @return The value of the axis from -1 to 1.
     */
    public double getRightX() {
        return (applyDeadband(RIGHT_XAXIS));
    }

    /**
     * Get the value of Y of the right joystick. The upward part of the axis is
     * positive.
     *
     * @return The value of the axis from -1 to 1.
     */
    public double getRightY() {
        return (-1 * applyDeadband(RIGHT_YAXIS));
    }

    /**
     * Get the value corresponding to the triggers. The left stick adds to it,
     * the right stick subtracts. Range -1 to 1.
     *
     * @param
     * @return The value corresponding to the triggers.
     */
    public double getTriggers() {
        return (getRawAxis(TRIGGERS));
    }
    
    /**
     * Get the value corresponding to the DPAD x-axis. The left stick adds to it,
     * the right stick subtracts. Range -1 to 1.
     *
     * @param
     * @return The value corresponding to the DPAD.
     */
    public double getDPAD() {
        return (getRawAxis(DPAD_LR));
    }

    /**
     * Get the current state of the A button.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getAValue() {
        return (getRawButton(1));
    }    

    /**
     * Get the current state of the B button.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getBValue() {
        return (getRawButton(2));
    }

    /**
     * Get the current state of the X button.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getXValue() {
        return (getRawButton(3));
    }

    /**
     * Get the current state of the Y button.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getYValue() {
        return (getRawButton(4));
    }

    /**
     * Get the current state of the Left Bumper.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getLeftBumperValue() {
        return (getRawButton(5));
    }

    /**
     * Get the current state of the Right Bumper.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getRightBumperValue() {
        return (getRawButton(6));
    }

    /**
     * Get the current state of the Back button.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getBackValue() {
        return (getRawButton(7));
    }

    /**
     * Get the current state of the Start button.
     *
     * @param
     * @return The current state of the button
     */
    public boolean getStartValue() {
        return (getRawButton(8));
    }


    ////////////////Get Raw Button Methods to be used by Command/Subsystem Interface
    /**
     * Get A Button
     *
     * @param
     * @return
     * @return A Button
     */
    public JoystickButton getAButton() {
        return (new JoystickButton(this, A_BUTTON));
    }

    /**
     * Get B Button
     *
     * @param
     * @return
     * @return B Button
     */
    public JoystickButton getBButton() {
        return (new JoystickButton(this, B_BUTTON));
    }

    /**
     * Get X Button
     *
     * @param
     * @return X Button
     */
    public JoystickButton getXButton() {
        return (new JoystickButton(this, X_BUTTON));
    }

    /**
     * Get Y Button
     *
     * @param
     * @return Y Button
     */
    public JoystickButton getYButton() {
        return (new JoystickButton(this, Y_BUTTON));
    }

    /**
     * Get Back Button
     *
     * @param
     * @return Back Button
     */
    public JoystickButton getBackButton() {
        return (new JoystickButton(this, BACK_BUTTON));
    }

    /**
     * Get Start Button
     *
     * @param
     * @return Start Button
     */
    public JoystickButton getStartButton() {
        return (new JoystickButton(this, START_BUTTON));
    }

    /**
     * Get Left Bumper
     *
     * @param
     * @return Left Bumper
     */
    public JoystickButton getLeftBumper() {
        return (new JoystickButton(this, LEFT_BUMPER));
    }

    /**
     * Get Right Bumper
     *
     * @param
     * @return Right Bumper
     */
    public JoystickButton getRightBumper() {
        return (new JoystickButton(this, RIGHT_BUMPER));
    }

    ////////////////End Raw Button Methods for Command/Subsystem Interface
    /**
     * Get the buttons of the controller, to be used by other functions.
     *
     * The buttons on the controller follow this mapping 1: A 2: B 3: X 4: Y 5:
     * Left Bumper 6: Right Bumper 7: Back 8: Start 9: Left Joystick 10: Right
     * Joystick
     *
     * @param button The button to get the value of. Range 1-10.
     * @return The state of the button.
     */
    public boolean getRawButton(int button) {
        return (super.getRawButton(button));
    }

    /**
     * Get the raw axes of the controller, to be used by other functions.
     *
     * The axis on the controller follow this mapping 1: Left Stick X Axis
     * -Left:Negative ; Right: Positive 2: Left Stick Y Axis -Up: Negative ;
     * Down: Positive 3: Triggers -Left: Positive ; Right: Negative 4: Right
     * Stick X Axis -Left: Negative ; Right: Positive 5: Right Stick Y Axis -Up:
     * Negative ; Down: Positive 6: Directional Pad (Not recommended, buggy)
     *
     * @param axis The axis to get the value of. Range 1-6.
     * @return The value of the axis from -1 to 1.
     */
    public double getRawAxis(int axis) {
        return (double) (super.getRawAxis(axis));
    }

    /**
     * Normalize outputs to be between -1 and 1.
     *
     * @param num The value to normalize.
     * @return The normalized value.
     */
    public double limit(double num) {
        if (num > 1) {
            num = 1;
        } else if (num < -1) {
            num = -1;
        }
        return num;
    }
    
    public double applyDeadband(int axis) {
        if(Math.abs(m_pad.getRawAxis(axis)) < .2) {
            return 0;
        } else {
            return m_pad.getRawAxis(axis);
        }
    }          
}