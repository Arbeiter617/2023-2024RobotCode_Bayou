package frc.robot.commands.limelightCommands.limelightAutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.subsystems.shooterPigeon;

public class amp extends Command {
    //variable speeds//
    double neededSpeed = .225;
    double alignSpeed = .1;
    double actuatorNeededSpeed = .65;
    public static double outakeSpeed = .3;

    //needed specific angles//
    double neededAngle = 50;

    //offsets//
    private double yOffset = 2;
    private double xOffset = 2;

    //specific values on the axis//
    double neededYValue = 10;
    double neededXValue = 0;

    //booleans//
    boolean wenchIsAligned = false;

    //calling subsystems//
    shooterPigeon s_shooterPigeon;

     public amp() {
        this.s_shooterPigeon = s_shooterPigeon;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {

        //spin up shooter//
        shooterShoot.runShooter(neededSpeed);

        if(shooterPigeon.roll > (neededAngle + yOffset)) {
           shooterActuator.runActuator(actuatorNeededSpeed);
           wenchIsAligned = false;
        } else if(shooterPigeon.roll < (neededAngle - yOffset)) {
            shooterActuator.runActuator(-actuatorNeededSpeed);
            wenchIsAligned = false;
        } else {
            shooterActuator.stopActuator();
            wenchIsAligned = true;
        }

        ////TO USE THIS FEATURE, SET "drivepls" TO TRUE IN "readAprilTags" FILE////
        if(limelightReadingTool.idRead != -1 && wenchIsAligned) {
            //check limelight X position//
            if(limelightReadingTool.xValue > (neededXValue + xOffset)) {
                //move left//
                TeleopSwerve.movementSpeedAutoStrafe = alignSpeed;
            } else if(limelightReadingTool.xValue < (neededXValue - xOffset)) {
                //move right//
                TeleopSwerve.movementSpeedAutoStrafe = -alignSpeed;
            } else {
                //stop//
                TeleopSwerve.movementSpeedAutoStrafe = 0;
            }
        }  
    }
}