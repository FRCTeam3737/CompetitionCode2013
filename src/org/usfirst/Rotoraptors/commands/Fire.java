/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.Rotoraptors.commands;

import org.usfirst.Rotoraptors.Constants;
import org.usfirst.Rotoraptors.commands.injector.InjectFrisbee;
import org.usfirst.Rotoraptors.commands.shooter.*;
import org.usfirst.Rotoraptors.commands.indexer.*;

/**
 *
 * @author Daniel
 */
public class Fire extends CommandBase {
    
    boolean frisbeeLoaded = false;
    boolean indexerInPosition = false;
    boolean injectorClear = false;
        
    private int iterations = 1;
    private int currentIteration = 0;
    private int state;
    
    //static state variables, used in state machine
    public static final int checking = 0;
    public static final int preparing = 1;
    public static final int ready = 2;
    public static final int injecting = 3;
    public static final int indexing = 4;
    public static final int finished = 5;
                           
    public Fire() {
        // Use requires() here to declare subsystem dependencies
        requires(injector);
        requires(indexer);
    }
    
    public Fire(int numIterations) {
        // Use requires() here to declare subsystem dependencies
        requires(injector);
        requires(indexer);
                
        iterations = numIterations;
        currentIteration = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setInterruptible(false);
        
        state = checking;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch(state) {
            case checking:
                if(indexer.getProxSensor() && indexer.getShooterOptical() && injector.getInjectorLimit()) {
                    state = ready;
                } else {
                    if(!indexer.getProxSensor()) {
                        new AdvanceDownUntil();
                        state = preparing;
                    } else if(!indexer.getShooterOptical()) {
                        new AdvanceUp();
                        state = preparing;
                } else if(!injector.getInjectorLimit()) {
                    injector.retract();
                    state = preparing;
                }
                }
            case preparing: {
                if(indexer.getProxSensor() && indexer.getShooterOptical() && injector.getInjectorLimit()) {
                    state = ready;
                }
            }
            case ready: {
                new Shoot(Constants.Shooter.SHOOTER_TYP_SPEED);
                state = injecting;
            }
            case injecting: {
                if(shooter.onTarget()) {
                    new InjectFrisbee();
                }
            }
            case finished: {
                currentIteration++;
                state = checking;
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (currentIteration >= iterations);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
