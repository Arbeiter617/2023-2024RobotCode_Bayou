package frc.robot.commands.automatedCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.ampBarCommands.ampAssistControl;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;

public class automatedShooting extends Command {
    public static boolean automatedShooting = false;
    double shooterSpeed;
    double outakeSpeed;
     public automatedShooting() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.copilot.getRawButton(controls.automatedShootingButton)) {
            //is holding button//
            automatedShooting = true;
            shooterShoot.runShooter(shooterSpeed);
        } else {
            if(automatedShooting) {
                //had button held//
                if(!colorSensorRun.pieceIsFound) {
                    shooterShoot.stopShooter();
                    intakeIntake.stopintakeSpeed();
                    automatedShooting = false;
                } else {
                    //run outake//
                    intakeIntake.runIntakeSpeed(outakeSpeed * -1);
                }
                
            }
        }
      
    }

}