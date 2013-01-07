/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.Rotoraptors.controls.hids;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;

/**
 *
 * @author Daniel
 */

public class Cypress {    
    private DriverStationEnhancedIO m_io;
    private boolean fault = false;
    
    public Cypress(){
        m_io = DriverStation.getInstance().getEnhancedIO();
    }
    
//    public boolean getBlueButton() {
//        return !getDigital(BLUE_BUTTON);
//    }

    public boolean getDigital(int which) {
        //if(!fault) {
        try {
            return m_io.getDigital(which);
        } catch (Exception e) {
            e.printStackTrace();
            fault = true;
            return false;
        }
//        } else {
//            return false;
//        }
    }

    /**
     * Get the value of the analog channel specified
     * @param which Which channel
     * @return the value of that channel, usually between 0 and 3.3
     */
    public double getAnalog(int which) {
        //if(!fault) {
        try {
            return m_io.getAnalogIn(which);
        } catch (Exception e) {
            System.out.println(e);
            fault = true;
            return 0.0;
        }
//        } else {
//            return 0.0;
//        }
    }

   /**
     * Return false during normal activity, true if the board is not plugged in
     * @return false during normal activity, true if the board is not plugged in
     */
    public boolean getFault() {
        try {
            fault = false;
            if (m_io.getFirmwareVersion() == 0) {
                fault = true;
            }
        } catch (EnhancedIOException eioe) {
            eioe.printStackTrace();
            fault = true;
        }
        return fault;
    }

    public void setIndicators(boolean state) {
        if (!fault) {
            try {
                m_io.setLED(1, state);
                m_io.setLED(2, state);
                m_io.setLED(3, state);
                m_io.setLED(4, state);
                m_io.setLED(5, state);
                m_io.setLED(6, state);
                m_io.setLED(7, state);
                m_io.setLED(8, state);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
