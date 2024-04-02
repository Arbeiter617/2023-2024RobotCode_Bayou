package frc.robot.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.controls.controls;
import frc.robot.controls.gunControls;
import frc.robot.subsystems.shooterPigeon;


public class shooterActuatorA extends Command {
    /*Calling Subsystems */
    shooterPigeon s_shooterPigeon;
    private shooterPigeon s_ShooterPigeon;

    //Offsets//
    double offsetVal = .25;
    double neededHeight = 45;
    double actuatorSpeed = 1;

    public static boolean isAtPos = false;

     public shooterActuatorA() {
        this.s_shooterPigeon = s_shooterPigeon;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
            if(shooterPigeon.roll < neededHeight - offsetVal) {
                //go up//\
                    shooterActuator.runActuator(-actuatorSpeed);
                
            } else if(shooterPigeon.roll > neededHeight + offsetVal) {
                //go down//
                    shooterActuator.runActuator(actuatorSpeed);
                
            } else {
                //stop//
               shooterActuator.stopActuator();
            }

    }


    public boolean isFinished() {
        return isAtPos;
    }

    public void end() {

    }
}
