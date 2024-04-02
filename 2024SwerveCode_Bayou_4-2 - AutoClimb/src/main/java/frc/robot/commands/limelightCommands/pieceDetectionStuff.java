package frc.robot.commands.limelightCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Swerve;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.controls.controls;
import frc.robot.subsystems.colorSensor;

public class pieceDetectionStuff extends Command {
    Swerve s_Swerve;
    double neededXValue = 0;
    double xOffset = 2;
    double alignSpeedRot = .15;
    double alignSpeedTran = .15;
    public static double value;

    boolean rotationaligned = false;
    boolean gyrosaved = false;
    boolean gyroReset = false;

    boolean gotPiece = false;
     public pieceDetectionStuff() {
        this.s_Swerve = s_Swerve;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        //here, we will align with the piece automatically//
        if(RobotContainer.driver.getRawButton(controls.pieceDetectionToggle) && !gotPiece) {

            RobotContainer.rotateToPiece = true;
                //check limelight X position//
                rotationaligned = false;
            if(limelightReadingTool.xValuePD > (neededXValue + xOffset)) {
                //move left//
                TeleopSwerve.movementSpeedAutoRotation = -alignSpeedRot;
            } else if(limelightReadingTool.xValuePD < (neededXValue - xOffset)) {
                //move right//
                rotationaligned = false;
                TeleopSwerve.movementSpeedAutoRotation = alignSpeedRot;
            } else {
                //stop//
                TeleopSwerve.movementSpeedAutoRotation = 0;
                rotationaligned = true;
            }

            //rotation is aligned, now go towards piece//
            if(rotationaligned) {
                //drive forward//
                if(!gyrosaved) {
                    //save gyro data//
                    TeleopSwerve.saveGyroTeleop();
                    gyrosaved = true;
                } else {
                    if(!gyroReset) {
                        TeleopSwerve.zeroAutoGyro();
                    }   
                }

            if(!colorSensorRun.pieceIsFound) {
                //move forward//
                TeleopSwerve.movementSpeedAutoTransform = alignSpeedTran;
            } else {
                //stop//
                TeleopSwerve.movementSpeedAutoTransform = 0;
                //set gyro value again//
                TeleopSwerve.resetGyroTeleop();
                gotPiece = true;
            }
                
            }
        } else {
            if(!readAprilTags.isSpeaker) {
                RobotContainer.rotateToPiece = false;
            }
            gyroReset = false;
            gyrosaved = false;
            gotPiece = false;
        }
    }
}