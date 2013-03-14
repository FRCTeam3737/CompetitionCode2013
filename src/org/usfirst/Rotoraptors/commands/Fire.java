/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.indexer.AdvanceUp;
import org.usfirst.Rotoraptors.commands.injector.InjectFrisbee;
import org.usfirst.Rotoraptors.commands.screwDrive.HoldPosition;
import org.usfirst.Rotoraptors.commands.shooter.Shoot;

/**
 *
 * @author Daniel
 */
public class Fire extends CommandGroup {
    
    public Fire() {
        addSequential(new HoldPosition());
        addParallel(new Shoot(Constants.Shooter.SHOOTER_TYP_SPEED, 10));
        addSequential(new InjectFrisbee());
        addSequential(new AdvanceUp());
    }
    
    public Fire(int iterations) {
        
        for(int i = 1; i <= iterations; i++) {
            addSequential(new HoldPosition());
            addParallel(new Shoot(Constants.Shooter.SHOOTER_TYP_SPEED, 10));
            addSequential(new InjectFrisbee());
            addSequential(new AdvanceUp());
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
}
