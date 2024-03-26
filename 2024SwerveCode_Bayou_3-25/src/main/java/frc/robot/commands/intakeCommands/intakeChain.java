package frc.robot.commands.intakeCommands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.controls.controls;

public class intakeChain extends Command {
    /*Editable Values */
    double intakeUpSpeed;
    double intakeDownSpeed;
    double offsetVal = .1;
    double highestIntakePoint = -4;
    double lowestIntakePoint = -12;
    double upSpeed = .35;
    double downSpeed =-.5;

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

        if(canManuallyMove) {
            if(!colorSensorRun.pieceIsFound) {
                //doesnt have a piece//
                if(RobotContainer.copilot.getRawAxis(controls.intakeChainControlAxis) < 0.0 - offsetVal && encoderVal < highestIntakePoint) {
                    //go up//
                    //System.out.println("test1");
                    if(encoderVal > -10) {
                        runIntake((upSpeed) / 2);
                    } else {
                        runIntake(upSpeed);
                    }
                } else if(RobotContainer.copilot.getRawAxis(controls.intakeChainControlAxis) > 0.0 + offsetVal && encoderVal > lowestIntakePoint) {
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
                if(RobotContainer.copilot.getRawAxis(controls.intakeChainControlAxis) > 0.0 + offsetVal && encoderVal > lowestIntakePoint) {
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
    
    public void runIntake(double speed) {
        Constants.intakeMotorUp.set(speed);
    }
       
    public void stopIntake() {
        Constants.intakeMotorUp.set(0);
    }

    public void resetEncoder() {
        if(RobotContainer.copilot.getRawButton(controls.resetEncoder)) {
            Constants.intakeMotorUp.getEncoder().setPosition(-4);
        }
    }

}
