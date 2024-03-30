package frc.robot.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.TeleopSwerve;

public class autoCommandsToRun extends Command {

     public autoCommandsToRun() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        //System.out.println("Test");
        RobotContainer.colorSensorRun.execute();
        RobotContainer.limelightReadingTool.execute();
        RobotContainer.intakeChain.execute();
        //RobotContainer.seekingPieces.execute();
        //RobotContainer.commandRunnerTeleopSwerve.execute();
    }
}
