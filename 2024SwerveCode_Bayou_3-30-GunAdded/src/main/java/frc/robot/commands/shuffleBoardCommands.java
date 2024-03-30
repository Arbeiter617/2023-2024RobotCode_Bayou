package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.automatedCommands.automatedShooting;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.readAprilTags;
import frc.robot.commands.limelightCommands.toggleLimelight;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
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
        SmartDashboard.putNumber("shooter speed", automatedShooting.shooterSpeed);
        
    }
}
