/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.utilities;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author Daniel
 */

public class DummyPIDOutput implements PIDOutput{
    double output;
    
    public DummyPIDOutput()
    {
        output = 0;
    }

    public void pidWrite(double output) {
        this.output = output;
    }

    public double getOutput() {
        return output;
    }
}
