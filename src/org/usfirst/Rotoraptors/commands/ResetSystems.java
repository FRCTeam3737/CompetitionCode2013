/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.Rotoraptors.commands.shooter.ResetScissorLift;

/**
 *
 * @author Daniel
 */
public class ResetSystems extends CommandGroup {
    
    public ResetSystems() {
        addSequential(new ResetScissorLift());
        //addSequential(new);
    }
}
