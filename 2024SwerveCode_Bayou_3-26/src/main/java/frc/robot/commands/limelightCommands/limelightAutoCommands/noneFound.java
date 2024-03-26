package frc.robot.commands.limelightCommands.limelightAutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.subsystems.shooterPigeon;

public class noneFound extends Command {
    //variable speeds//
    double neededSpeed = .225;

     public noneFound() {
        
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {

      shooterActuator.runActuator(neededSpeed);
    }
}