package frc.robot.commands.automatedCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.controls.gunControls;

public class automatedIntake extends Command {
    public static boolean automatedIntake = false;
     public automatedIntake() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.copilot_flightStick.getRawButton(gunControls.automatedIntakeButton) && !colorSensorRun.pieceIsFound) {
            //is holding button//
            automatedIntake = true;
            intakeDownCommand();
            //run intake as well...//
            intakeIntake.runIntakeSpeed(intakeIntake.highestIntakeSpeed);
            shooterActuator.runActuator(.5);
        } else {
            if(automatedIntake) {
                //had button held//
                intakeIntake.stopintakeSpeed();
                intakeUpCommand();
            }
        }
      
    }

    public static void intakeDownCommand() {
        if(intakeChain.encoderVal > intakeChain.lowestIntakePoint) {
                if(intakeChain.encoderVal < -6) {
                    intakeChain.runIntake((intakeChain.downSpeed) / 4);
                } else {
                    intakeChain.runIntake(intakeChain.downSpeed);
                }
            } else {
                intakeChain.stopIntake();
            }
    }

    public static void intakeUpCommand() {
        if(intakeChain.encoderVal < intakeChain.highestIntakePoint) {
            if(intakeChain.encoderVal > -10) {
                intakeChain.runIntake((intakeChain.upSpeed) / 2);
            } else {
                intakeChain.runIntake(intakeChain.upSpeed);
            }
        } else {
            //at highest point... turn off command//
            intakeChain.stopIntake();
            automatedIntake = false;
        }
    }

}