/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.controls.hids;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 * @author Daniel
 */
public class Attack3 extends Joystick {
    
    private Joystick m_stick;
    
    public Attack3(int port){
        super(port);
        m_stick = new Joystick(port);
    }
    
    public double getXAxis() {
        return m_stick.getRawAxis(X_AXIS);
    }
    
    public double getYAxis() {
        return m_stick.getRawAxis(Y_AXIS);
    }
    
//    public double getThrottle() {
//        return m_stick.getRawAxis(THROTTLE);
//    }
    
    public double getAngle() {
        return m_stick.getDirectionDegrees();
    }
    
    public boolean getTriggerBtn() {
        return m_stick.getRawButton(TRIGGER);
    }
    
    //Create buttons
    public Button trigger = new JoystickButton(m_stick, TRIGGER);
    public Button north = new JoystickButton(m_stick, BUTTON_NORTH);
    public Button south = new JoystickButton(m_stick, BUTTON_SOUTH);
    public Button east = new JoystickButton(m_stick, BUTTON_EAST);
    public Button west = new JoystickButton(m_stick, BUTTON_WEST);
    public Button base_ene = new JoystickButton(m_stick, BUTTON_BASE_ENE);
    public Button base_ese = new JoystickButton(m_stick, BUTTON_BASE_ESE);
    public Button base_sse = new JoystickButton(m_stick, BUTTON_BASE_SSE);
    public Button base_ssw = new JoystickButton(m_stick, BUTTON_BASE_SSW);
    public Button base_wnw = new JoystickButton(m_stick, BUTTON_BASE_WNW);
    public Button base_wsw = new JoystickButton(m_stick, BUTTON_BASE_WSW);
    
    public boolean getButton(int button) {
        return m_stick.getRawButton(button);
    }
       
    //Axis Indexes
    public static final int
            X_AXIS = 1,
            Y_AXIS = 2,
            THROTTLE = 3;
    
    //Buttons
    public static final int
            //Buttons on joystick
            TRIGGER = 1,
            //POV buttons on head of stick
            BUTTON_SOUTH = 2,
            BUTTON_NORTH = 3,
            BUTTON_WEST = 4,
            BUTTON_EAST = 5,
            //Pair of buttons left of stick
            BUTTON_BASE_WNW = 6,
            BUTTON_BASE_WSW = 7,
            //Pair of buttons below stick
            BUTTON_BASE_SSW = 8,
            BUTTON_BASE_SSE = 9,
            //Pair of buttons right of stick
            BUTTON_BASE_ESE = 10,
            BUTTON_BASE_ENE = 11;
}
