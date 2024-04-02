package frc.robot.commands.limelightCommands.limelightAutoCommands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.toggleLimelight;
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
    
    public static double neededSpeed = 1;

    double time;
    public static boolean timeSet = false;

     public speaker() {}
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        RobotContainer.copilot_Xbox.setRumble(RumbleType.kBothRumble, 1);
        if(limelightReadingTool.idRead != -1) {
        if(limelightReadingTool.yValue > (centerYValue + yOffset)) {
            shooterActuator.runActuator(-actuatorNeededSpeed);
            } else if(limelightReadingTool.yValue < (centerYValue - yOffset)) {
            shooterActuator.runActuator(actuatorNeededSpeed);
        } else {
            shooterActuator.stopActuator();
            //run outake//
            if(!timeSet) {
                setTime();
            }

            if(System.currentTimeMillis() > time + 500) {
                intakeIntake.runIntakeSpeed(intakeIntake.highestOutakeSpeed);
                
            }
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

    void setTime() {
        System.out.println("time set");
        time = System.currentTimeMillis();
        timeSet = true;

    }
 
}
