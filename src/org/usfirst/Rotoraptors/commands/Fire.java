/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.Rotoraptors.commands.indexer.IndexUp;
import org.usfirst.Rotoraptors.commands.injector.InjectFrisbee;
import org.usfirst.Rotoraptors.commands.shooter.Spin;

/**
 *
 * @author Daniel
 */
public class Fire extends CommandGroup {
    
    // This class fires one frisbee
    
    public Fire() {
        double m_speed;
        m_speed = 1; //scaleThrottle(OI.driverJoystick.getThrottle());
                
        addParallel(new Spin(m_speed, 2.8));
        // Begins spinning for 2.8 seconds
        addSequential(new WaitCommand(1.9));
        // Waits for only 1.9 seconds before
        addSequential(new InjectFrisbee());
        // Injecting the frisbee
        addSequential(new WaitCommand(.15));
        // Wait .15 seconds and then
        addSequential(new IndexUp());        
        // Index up, loading next frisbee into position
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

