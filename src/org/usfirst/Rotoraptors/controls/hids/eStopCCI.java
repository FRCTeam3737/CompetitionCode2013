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
public class eStopCCI {
    private Joystick m_cci;
    
    public eStopCCI(int port) {
        m_cci = new Joystick(port);
    }
    
    //Create buttons
    public Button devToggle = new JoystickButton(m_cci, DEV_MODE);
    public Button feedToggle = new JoystickButton(m_cci, FEED_PICKUP);
    public Button angleToggleM = new JoystickButton(m_cci, RAISE_LOWER);
    public Button zeroPos = new JoystickButton(m_cci, GO_TO_ZERO_POSITION);
    public Button feedPos = new JoystickButton(m_cci, GO_TO_FEED_POSITION);
    public Button goalPos = new JoystickButton(m_cci, GO_TO_GOAL_POSITION);
    public Button pyrPos = new JoystickButton(m_cci, GO_TO_PYRAMID_POSITION);
//    public Button base_sse = new JoystickButton(m_cci, BUTTON_BASE_SSE);
//    public Button base_ssw = new JoystickButton(m_cci, BUTTON_BASE_SSW);
//    public Button base_wnw = new JoystickButton(m_cci, BUTTON_BASE_WNW);
//    public Button base_wsw = new JoystickButton(m_cci, BUTTON_BASE_WSW);
    
    public boolean getDigital(int digital) {
        return m_cci.getRawButton(digital);
    }
    
    public double getAnalog(int analog) {
        return m_cci.getRawAxis(analog);
    }
       
    //Axis Indexes
    public static final int
            SPEED_LIMIT = 1;
    
    //Buttons
    public static final int
            //Buttons
            GO_TO_ZERO_POSITION = 1,
            GO_TO_FEED_POSITION = 2,
            GO_TO_GOAL_POSITION = 3,
            GO_TO_PYRAMID_POSITION = 4,
            //Toggles
            DEV_MODE = 6,
            FEED_PICKUP = 7,
            RAISE_LOWER = 8;
}
