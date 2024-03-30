package frc.robot.commands.intakeCommands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.automatedCommands.automatedIntake;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.controls.controls;
import frc.robot.controls.gunControls;

public class intakeChain extends Command {
    /*Editable Values */
    double intakeUpSpeed;
    double intakeDownSpeed;
    double offsetVal = .1;
    public static double highestIntakePoint = 0;
    public static double lowestIntakePoint = -4;
    public static double upSpeed = .35;
    public static double downSpeed =-.35;

    /*Encoder Val */
    public static double encoderVal;
    /*Booleans */
    public static boolean canManuallyMove = true;
     public intakeChain() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        encoderVal = Constants.intakeMotorUp.getEncoder().getPosition();

        if(canManuallyMove && !automatedIntake.automatedIntake) {
            if(!colorSensorRun.pieceIsFound) {
                //doesnt have a piece//
                if(RobotContainer.copilot_Xbox.getRawButton(gunControls.intakeChainButtonUp) && encoderVal < highestIntakePoint) {
                    //go up//
                    //System.out.println("test1");
                    if(encoderVal > -10) {
                        runIntake((upSpeed) / 2);
                    } else {
                        runIntake(upSpeed);
                    }
                } else if(RobotContainer.copilot_Xbox.getRawButton(gunControls.intakeChainButtonDown) && encoderVal > lowestIntakePoint) {
                    //go down//
                    if(encoderVal < -6) {
                        runIntake((downSpeed) / 4);
                    } else {
                        runIntake(downSpeed);
                    }
                } else {
                    //stop//
                    stopIntake();
                }   
            } else {
                //has a piece//
                if(RobotContainer.copilot_Xbox.getRawButton(gunControls.intakeChainButtonDown) && encoderVal > lowestIntakePoint) {
                    //go down//
                    if(encoderVal < -8) {
                        runIntake((downSpeed) / 2);
                    } else {
                        runIntake(downSpeed);
                    }
                } else {
                    if(encoderVal < highestIntakePoint) {
                        //go up automatically//
                        if(encoderVal > -10) {
                            runIntake((upSpeed) / 2);
                        } else {
                            runIntake(upSpeed);
                        }
                    } else {
                        stopIntake();
                    }
                }
            }
        }
        resetEncoder();
    }
    
    public static void runIntake(double speed) {
        Constants.intakeMotorUp.set(speed);
    }
       
    public static void stopIntake() {
        Constants.intakeMotorUp.set(0);
    }

    public void resetEncoder() {
        if(RobotContainer.copilot_Xbox.getRawButton(gunControls.resetEncoder)) {
            Constants.intakeMotorUp.getEncoder().setPosition(0);
        }
    }

}
