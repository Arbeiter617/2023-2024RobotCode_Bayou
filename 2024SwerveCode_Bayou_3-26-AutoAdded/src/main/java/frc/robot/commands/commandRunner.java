package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class commandRunner extends Command {

     public commandRunner() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        RobotContainer.climberLeft.execute();
        RobotContainer.climberRight.execute();
        RobotContainer.intakeChain.execute();
        RobotContainer.intakeIntake.execute();
        RobotContainer.shooterActuator.execute();
        RobotContainer.shooterShoot.execute();
        RobotContainer.canRunFileManager.execute();
        RobotContainer.limelightReadingTool.execute();
        RobotContainer.accuracyLogger.execute();
        RobotContainer.shuffleBoardCommands.execute();
        RobotContainer.colorSensorRun.execute();
        RobotContainer.toggleLimelight.execute();
        RobotContainer.ampAssistControl.execute();
        RobotContainer.readAprilTags.execute();
        RobotContainer.pieceDetectionStuff.execute();
        RobotContainer.ampControl.execute();
        RobotContainer.climberLeft.execute();
        RobotContainer.climberRight.execute();
        RobotContainer.automatedIntake.execute();
        RobotContainer.automatedShooting.execute();
    }
}
