package frc.robot.commands.limelightCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Swerve;
import frc.robot.commands.TeleopSwerve;
import frc.robot.controls.controls;
import frc.robot.subsystems.colorSensor;

public class pieceDetectionStuff extends Command {
    Swerve s_Swerve;
    double neededXValue = 0;
    double xOffset = 2;
    double alignSpeed = .15;
    Pose2d value;
     public pieceDetectionStuff() {
        this.s_Swerve = s_Swerve;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        //here, we will align with the piece automatically//
        if(RobotContainer.driver.getRawButton(controls.pieceDetectionToggle)) {

            RobotContainer.rotateToPiece = true;
                //check limelight X position//
            if(limelightReadingTool.xValuePD > (neededXValue + xOffset)) {
                //move left//
                TeleopSwerve.movementSpeedAutoRotation = -alignSpeed;
            } else if(limelightReadingTool.xValuePD < (neededXValue - xOffset)) {
                //move right//
                TeleopSwerve.movementSpeedAutoRotation = alignSpeed;
            } else {
                //stop//
                TeleopSwerve.movementSpeedAutoRotation = 0;
            }
        } else {
            if(!readAprilTags.isSpeaker) {
                RobotContainer.rotateToPiece = false;
            }
        }
    }

    void saveOdom() {
        //saves odometry settings//
        value = s_Swerve.swerveOdometry.getPoseMeters();
        System.out.println(value);
    }
}