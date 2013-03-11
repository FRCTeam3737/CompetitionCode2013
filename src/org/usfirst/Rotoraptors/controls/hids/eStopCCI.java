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
    public Button feedToggle = new JoystickButton(m_cci, FEED_SHOOT);
    public Button visionToggle = new JoystickButton(m_cci, VISION);
    public Button indexUp = new JoystickButton(m_cci, INDEX_UP);
    public Button indexDown = new JoystickButton(m_cci, INDEX_DOWN);

    
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
            DEV_MODE = 1,
            INDEX_UP = 2,
            INDEX_DOWN = 3,
            FEED_SHOOT = 4,
            VISION = 5;
}
