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
        
    public boolean getDigital(int digital) {
        return m_cci.getRawButton(digital);
    }
    
    public double getAnalog(int analog) {
        return m_cci.getRawAxis(analog);
    }

}
