/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.utilities;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author Daniel
 */
public class Utils {
    
    public static double scaleAngleToValue(Gyro gyro) {
        double angle = gyro.getAngle();
        return angle + 360 * (int) (0.5-angle/360);
    }
    
    /**
* linearly interpolates the power value given to a victor motor
* (makes it get up to speed smoothly)
* @param desiredPower
* @return the lerp'd power to be sent to the robot
*/
    public static double victorLinearize(double desiredPower) {       
        
    final double DEADBAND_VALUE = 0.082;
    final double VICTOR_FIT_C1 = -1.56847;
    final double VICTOR_FIT_C2 = -5.46889;
    final double VICTOR_FIT_E1 = 0.437239;
    final double VICTOR_FIT_A1 = ( -( 125.0 * VICTOR_FIT_E1
                                                    + 125.0 * VICTOR_FIT_C1
                                                    - 116.00 / 125.0 ) );
    final double VICTOR_FIT_E2 = 2.24214;
    final double VICTOR_FIT_G2 = -0.042375;
    final double VICTOR_FIT_A2 = ( -125.0
                                                 * ( VICTOR_FIT_C2 + VICTOR_FIT_E1
                                                     + VICTOR_FIT_G2 ) - 116.00 ) / 125.0;
        // deadzone
        if ( desiredPower > DEADBAND_VALUE ) {
            desiredPower -= DEADBAND_VALUE;
        }
        else if ( desiredPower < -DEADBAND_VALUE ) {
            desiredPower += DEADBAND_VALUE;
        }
        else {
            return 0.0;
        }

        // move -1.0-1.0 into the range of (-1+deadband)-(1-deadband)s
        desiredPower = desiredPower / ( 1.0 - DEADBAND_VALUE );

        // x^2 --> x^7
        double desiredPower3 = desiredPower * desiredPower * desiredPower;
        double desiredPower5 = desiredPower3 * desiredPower * desiredPower;
        double desiredPower7 = desiredPower5 * desiredPower * desiredPower;

        // Calculate 5th order
        double answerOrder5 = ( VICTOR_FIT_A1 * desiredPower5
                                + VICTOR_FIT_C1 * desiredPower3
                                + VICTOR_FIT_E1 * desiredPower );

        // calculate 7th order
        double answerOrder7 = ( VICTOR_FIT_A2 * desiredPower7
                                + VICTOR_FIT_C2 * desiredPower5
                                + VICTOR_FIT_E2 * desiredPower3
                                + VICTOR_FIT_G2 * desiredPower );

        // average 5th and 6th together
        double answer = 0.85 * 0.5 * ( answerOrder7 + answerOrder5 )
                        + 0.15 * desiredPower * ( 1 - DEADBAND_VALUE );

        if ( answer > 0.001 ) {
            answer += DEADBAND_VALUE;
        }
        else if ( answer < -0.001 ) {
            answer -= DEADBAND_VALUE;
        }

        return answer;
    }
}

