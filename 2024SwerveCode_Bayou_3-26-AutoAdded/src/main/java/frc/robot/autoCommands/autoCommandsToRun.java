package frc.robot.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class autoCommandsToRun extends Command {

     public autoCommandsToRun() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        RobotContainer.colorSensorRun.execute();
        RobotContainer.limelightReadingTool.execute();
    }
}
