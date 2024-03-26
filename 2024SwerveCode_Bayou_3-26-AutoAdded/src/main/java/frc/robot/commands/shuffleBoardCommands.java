package frc.robot.commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest.RobotCentric;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.artificialLearningTools.accuracyLogger;
import frc.robot.commands.climberCommands.climberLeft;
import frc.robot.commands.climberCommands.climberRight;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.readAprilTags;
import frc.robot.commands.limelightCommands.toggleLimelight;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;
import frc.robot.subsystems.shooterPigeon;


public class shuffleBoardCommands extends Command {
    /*Calling Subsystems */
    shooterPigeon s_shooterPigeon;
    private shooterPigeon s_ShooterPigeon;

     public shuffleBoardCommands() {
        this.s_shooterPigeon = s_shooterPigeon;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        /*Put all needed values on to shufflboard HERE */
        SmartDashboard.putNumber("April Tag ID: ", limelightReadingTool.idRead);
        SmartDashboard.putBoolean("Is Stage: ", readAprilTags.isStage);
        SmartDashboard.putBoolean("Is Speaker: ", readAprilTags.isSpeaker);
        SmartDashboard.putBoolean("Toggle Limelight: ", toggleLimelight.toggleLimelightPos);
        SmartDashboard.putBoolean("Toggle Peace Detection: ", RobotContainer.driver.getRawButton(controls.pieceDetectionToggle));
        SmartDashboard.putNumber("Y Value For Speaker: ",  speaker.centerYValue);
        
    }
}
