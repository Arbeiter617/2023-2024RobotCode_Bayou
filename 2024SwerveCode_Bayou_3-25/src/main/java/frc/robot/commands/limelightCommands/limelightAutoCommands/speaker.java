package frc.robot.commands.limelightCommands.limelightAutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;

public class speaker extends Command {
    //specific values on axis//
    public static double centerYValue = -23;
    
    //offsets//
    double yOffset = 2;
    double xOffset = 3;

    double neededXValue = 0;

    //variable speeds//
    double actuatorNeededSpeed = .45;
    double rotationSpeed = .1;
    double alignSpeed = .15;
    
    double neededSpeed = 1;

     public speaker() {}
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        shooterShoot.runShooter(neededSpeed);
        if(limelightReadingTool.idRead != -1) {
        if(limelightReadingTool.yValue > (centerYValue + yOffset)) {
            shooterActuator.runActuator(-actuatorNeededSpeed);
            } else if(limelightReadingTool.yValue < (centerYValue - yOffset)) {
            shooterActuator.runActuator(actuatorNeededSpeed);
        } else {
            shooterActuator.stopActuator();
        } 

        if(limelightReadingTool.xValue > (neededXValue + xOffset)) {
                //move left//
                TeleopSwerve.movementSpeedAutoRotation = -alignSpeed;
            } else if(limelightReadingTool.xValue < (neededXValue - xOffset)) {
                //move right//
                TeleopSwerve.movementSpeedAutoRotation = alignSpeed;
            } else {
                //stop//
                TeleopSwerve.movementSpeedAutoRotation = 0;
            }
    } else {
        RobotContainer.noneFound.execute();
    }
    }
 
}
