/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.Fire;
import org.usfirst.Rotoraptors.commands.SequentialFire;
import org.usfirst.Rotoraptors.commands.screwDrive.ResetLift;
import org.usfirst.Rotoraptors.commands.screwDrive.SetAngle;

/**
 *
 * @author Daniel
 */
public class Auton0 extends CommandGroup {
    
    public Auton0() {
        
        addSequential(new ResetLift());
        addSequential(new SetAngle(Constants.Shooter.POSITION_1));
        addSequential(new Fire());
        addSequential(new SequentialFire());
        addSequential(new SequentialFire());

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
