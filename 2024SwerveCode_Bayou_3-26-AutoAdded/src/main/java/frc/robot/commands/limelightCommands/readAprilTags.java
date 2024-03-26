package frc.robot.commands.limelightCommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;

public class readAprilTags extends Command {
    public static double specificID;
    public static boolean isStage = false;
    public static boolean isSpeaker = false;

    public readAprilTags() {}

     @Override
    public void initialize() {}
     
     @Override
    public void execute() {
        specificID = limelightReadingTool.idRead;    
        //check tags//
        if(toggleLimelight.toggleLimelightPos) {
            RobotContainer.checkingArray.initialize();
            shooterShoot.runShooter(speaker.neededSpeed);
            limelightReadingTool.table.getEntry("ledMode").setNumber(0);
            if(isSpeaker) {
                RobotContainer.rotateToPiece = true;
                RobotContainer.speaker.execute();
            } else {
                System.out.println("NOTHING");
                RobotContainer.noneFound.execute();
            }
        } else {
                RobotContainer.drivepls = false;
            
            limelightReadingTool.table.getEntry("ledMode").setNumber(1);
        }

    }
}