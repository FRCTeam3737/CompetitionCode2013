/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.Rotoraptors.commands.indexer.IndexDown;
import org.usfirst.Rotoraptors.commands.indexer.IndexUp;

/**
 *
 * @author Daniel
 */
public class IndexerDownDownUp extends CommandGroup {
    
    // This class indexes down twice, up once, as a workaround to the innaccurate prox sensor
    // Now depreciated
    
    public IndexerDownDownUp() {
        addSequential(new IndexDown());
        addSequential(new IndexDown());
        addSequential(new IndexUp());
        
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command IndexerDownDownUp will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
