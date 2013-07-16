/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.Rotoraptors.commands.indexer.IndexUp;
import org.usfirst.Rotoraptors.commands.injector.InjectFrisbee;

/**
 *
 * @author Daniel
 */
public class SequentialFire extends CommandGroup {
    
    // This class is a modified "Fire" Command with less waiting in the beginning,
    // making the assumption that some momentum is already present
    
    public SequentialFire() {
        
        addSequential(new WaitCommand(.55));
        addSequential(new InjectFrisbee());
        addSequential(new WaitCommand(.075));
        addSequential(new IndexUp());
    }
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
