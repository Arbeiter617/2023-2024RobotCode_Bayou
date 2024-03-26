package frc.robot.commands.climberCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;

public class climberRight extends Command {
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
     public climberRight() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        actualSpeed = RobotContainer.driver.getRawAxis(controls.climberRightDownAxis);
        encoderVal = Constants.climberRight.getEncoder().getPosition();

        if(canManuallyMove) {
            if(RobotContainer.driver.getRawAxis(controls.climberRightDownAxis) < 0.0 - offsetVal) {
                //go down//
                runLeftClimber(-climberDownSpeed);
            } else if(RobotContainer.driver.getRawButton(controls.climberRightUpButton)) {
                //go up//
                runLeftClimber(actualSpeed);
            } else {
                //stop//
                stopLeftClimber();
            }
        }
    }
    
    public void runLeftClimber(double speed) {
        Constants.climberRight.set(speed);
    }
       
    public void stopLeftClimber() {
        Constants.climberRight.set(0);
    }

}
