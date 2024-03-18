package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
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
    }
}
