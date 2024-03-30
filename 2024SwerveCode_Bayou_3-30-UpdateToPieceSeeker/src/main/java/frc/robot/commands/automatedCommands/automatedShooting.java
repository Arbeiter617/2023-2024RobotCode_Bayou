package frc.robot.commands.automatedCommands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.ampBarCommands.ampAssistControl;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.toggleLimelight;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;
import frc.robot.controls.gunControls;

public class automatedShooting extends Command {
    public static boolean automatedShooting = false;
    public static double shooterSpeed = 1;
    double outakeSpeed = 1;

    double systemTime;
    boolean timeIsSet = false;

    int timeOffset = 500;
     public automatedShooting() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.copilot_flightStick.getRawButton(gunControls.automatedShootingButton)) {
            //is holding button//
            timeIsSet = false;
            automatedShooting = true;
            shooterShoot.runShooter(shooterSpeed);
            RobotContainer.copilot_Xbox.setRumble(RumbleType.kBothRumble, 1);
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

        if(!automatedShooting && !toggleLimelight.toggleLimelightPos) {
            RobotContainer.copilot_Xbox.setRumble(RumbleType.kBothRumble, 0);
        }
      
    }

    void timeSetter() {
        if(!timeIsSet) {
            systemTime = System.currentTimeMillis();
            timeIsSet = true;
        }
    }

    void hapetechControl() {
        //motors//
        RobotContainer.copilot_Xbox.setRumble(RumbleType.kBothRumble, 0);
    }

    void stopHaptechMotors() {
        RobotContainer.copilot_Xbox.setRumble(RumbleType.kBothRumble, 0);
    }

}