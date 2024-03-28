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
    double shooterSpeed = 1;
    double outakeSpeed = .8;

    double systemTime;
    boolean timeIsSet = false;

    int timeOffset = 500;
     public automatedShooting() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.copilot.getRawButton(controls.automatedShootingButton)) {
            //is holding button//
            timeIsSet = false;
            automatedShooting = true;
            shooterShoot.runShooter(shooterSpeed);
        } else {
            if(automatedShooting) {
                //had button held//
                if(!colorSensorRun.pieceIsFound) {
                    timeSetter();
                   if(System.currentTimeMillis() > systemTime + 500) {
                     shooterShoot.stopShooter();
                    intakeIntake.stopintakeSpeed();
                    automatedShooting = false;
                   } else {
                    intakeIntake.runIntakeSpeed(outakeSpeed * -1);
                   }
                } else {
                    //run outake//
                    intakeIntake.runIntakeSpeed(outakeSpeed * -1);
                }
                
            }
        }
      
    }

    void timeSetter() {
        if(!timeIsSet) {
            systemTime = System.currentTimeMillis();
            timeIsSet = true;
        }
    }

}