package frc.robot.commands.automatedCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.climberCommands.climberLeft;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.readAprilTags;
import frc.robot.controls.controls;

public class autoClimb extends Command {
    public static boolean autoClimbing = false;
    boolean isStage = false;
    double[] stageArray = {15, 16, 14, 12, 11, 13};

    //values//
    double areaValueForClimbers;

    double highestPoint;
    double lowestPoint;

    boolean climbersUp = false;
     public autoClimb() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.driver.getRawButton(controls.autoClimbButton)) {
            autoClimbing = true;
            RobotContainer.drivepls = true;

            //find tag//
            tagFinder();
            locateTag();
        } else {
            autoClimbing = false;
        }
      
    }

    void tagFinder() {
        for(int b = 0; b < stageArray.length; b++) {
            if(stageArray[b] == readAprilTags.specificID) {
                isStage = true;
            } else {
                isStage = false;
            }
        }
    }

    void locateTag() {
        if(!isStage) {
            //rotate to find tag//
            TeleopSwerve.movementSpeedAutoRotation = .25;
        } else {
            //found tag//
            centerOnTag();
        }
    }

    void centerOnTag() {
        TeleopSwerve.movementSpeedAutoRotation = 0;
        //zero gyro//
        TeleopSwerve.zeroAutoGyro();
        driveBasedOnSize();
    }

    void driveBasedOnSize() {
        if(!climbersUp) {
            if(limelightReadingTool.areaValue > areaValueForClimbers) {
                //backup//
                TeleopSwerve.movementSpeedAutoTransform = -.25;
            } else {
                TeleopSwerve.movementSpeedAutoTransform = 0;
                //climbers up//
                if(climberLeft.encoderVal < highestPoint) {
                    //go up//
                    climbersUp = false;
                    climberLeft.runLeftClimber(.5);
                } else {
                    //stop//
                    climbersUp = true;
                    climberLeft.stopLeftClimber();
                }
            }
        } else {
            if(limelightReadingTool.areaValue < areaValueForClimbers) {
                //go forward//
                TeleopSwerve.movementSpeedAutoTransform = .25;
            } else {
                TeleopSwerve.movementSpeedAutoTransform = 0;
                //climbers up//
                if(climberLeft.encoderVal > lowestPoint) {
                    //go down//
                    climberLeft.runLeftClimber(.5);
                } else {
                    //stop//
                    climberLeft.stopLeftClimber();
                }
            }
        }
    }

}