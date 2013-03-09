/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands.screwDrive;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.Rotoraptors.commands.chassis.DriveStraight;
import org.usfirst.Rotoraptors.commands.chassis.DriveToDistance;

/**
 *
 * @author Daniel
 */
public class Climb extends CommandGroup {
    
    public Climb() {
        addSequential(new SetAngle(0));
        addParallel(new WaitCommand(.2));
        addParallel(new DriveStraight(.1, .8));

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
