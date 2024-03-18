package frc.robot.commands.intakeCommands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;

public class intakeChain extends Command {
    /*Editable Values */
    double intakeUpSpeed;
    double intakeDownSpeed;
    double offsetVal;
    double highestIntakePoint = -4;
    double lowestIntakePoint = -12;
    double upSpeed = .35;
    double downSpeed =-.25;

    /*Encoder Val */
    public static double encoderVal;
    /*Booleans */
    boolean canManuallyMove = true;
     public intakeChain() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        encoderVal = Constants.intakeMotorUp.getEncoder().getPosition();

        if(canManuallyMove) {
            if(RobotContainer.copilot.getRawAxis(controls.intakeChainControlAxis) < 0.0 - offsetVal && encoderVal < highestIntakePoint) {
                //go up//
                if(encoderVal > -6) {
                    runIntake((upSpeed) / 2);
                } else {
                    runIntake(upSpeed);
                }
            } else if(RobotContainer.copilot.getRawAxis(controls.intakeChainControlAxis) > 0.0 + offsetVal && encoderVal > lowestIntakePoint) {
                //go down//
                if(encoderVal < -8) {
                    runIntake((downSpeed) / 2);
                } else {
                    runIntake(downSpeed);
                }
            } else {
                //stop//
                stopIntake();
            }
        }
    }
    
    public void runIntake(double speed) {
        Constants.intakeMotorUp.set(speed);
    }
       
    public void stopIntake() {
        Constants.intakeMotorUp.set(0);
    }

}
