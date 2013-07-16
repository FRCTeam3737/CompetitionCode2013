/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.Rotoraptors.commands.shooter.Deactivate;
import org.usfirst.Rotoraptors.commands.shooter.Spin;

/**
 *
 * @author Daniel
 */
public class FireAll extends CommandGroup {
    
    public FireAll() {
    
    addSequential(new Fire());
    // Goes through the process of firing one frisbee via the default fire command
    addParallel(new Spin(1, true));
    // Goes into a permanent spin
    addSequential(new SequentialFire());
    // Activates a modified fire one frisbee command with less waiting to spin up,
    // since some momentum is already present
    addSequential(new SequentialFire());
    // ^^
    addSequential(new SequentialFire());
    // ^^
    addParallel(new Deactivate());
    // Deactivates the shooter
    
    
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
}

