/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.Rotoraptors.commands.indexer.*;
import org.usfirst.Rotoraptors.commands.shooter.*;

/**
 *
 * @author Daniel
 */
public class Shoot extends CommandGroup {
    
    public Shoot() {
        addSequential(new SpinUp(), 4);
        addSequential(new InjectFrisbee());
        addSequential(new RetractInjector());
        
    }
}
