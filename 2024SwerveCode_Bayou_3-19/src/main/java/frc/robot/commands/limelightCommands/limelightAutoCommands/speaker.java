package frc.robot.commands.limelightCommands.limelightAutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;

public class speaker extends Command {
    //specific values on axis//
    public static double centerYValue = -22;
    
    //offsets//
    double yOffset = 2;

    //variable speeds//
    double actuatorNeededSpeed = .45;
    double rotationSpeed = .1;
    
    double neededSpeed = 1;

     public speaker() {}
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        shooterShoot.runShooter(neededSpeed);

        if(limelightReadingTool.yValue > (centerYValue + yOffset)) {
            shooterActuator.runActuator(-actuatorNeededSpeed);
            } else if(limelightReadingTool.yValue < (centerYValue - yOffset)) {
            shooterActuator.runActuator(actuatorNeededSpeed);
        } else {
            shooterActuator.stopActuator();
        }  
    }
 
}
