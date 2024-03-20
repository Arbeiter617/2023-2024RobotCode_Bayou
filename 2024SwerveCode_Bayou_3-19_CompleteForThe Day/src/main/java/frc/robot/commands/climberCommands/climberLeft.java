package frc.robot.commands.climberCommands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;

public class climberLeft extends Command {
    /*Editable Values */
    double climberDownSpeed = .5;
    double offsetVal;
    double highestClimbPoint;
    double lowestClimbPoint;
    double actualSpeed;

    /*Encoder Val */
    public static double encoderVal;
    /*Booleans */
    boolean canManuallyMove = true;
     public climberLeft() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        actualSpeed = RobotContainer.driver.getRawAxis(controls.climberLeftUpAxis);
        encoderVal = Constants.climberLeft.getEncoder().getPosition();

        if(canManuallyMove) {
            if(RobotContainer.driver.getRawAxis(controls.climberLeftUpAxis) < 0.0 - offsetVal && encoderVal < highestClimbPoint) {
                //go up//
                runLeftClimber(actualSpeed);
            } else if(RobotContainer.copilot.getRawAxis(controls.climberLeftDownButton) > 0.0 + offsetVal && encoderVal > lowestClimbPoint) {
                //go down//
                runLeftClimber(-climberDownSpeed);
            } else {
                //stop//
                stopLeftClimber();
            }
        }
    }
    
    public void runLeftClimber(double speed) {
        Constants.climberLeft.set(speed);
    }
       
    public void stopLeftClimber() {
        Constants.climberLeft.set(0);
    }

}
