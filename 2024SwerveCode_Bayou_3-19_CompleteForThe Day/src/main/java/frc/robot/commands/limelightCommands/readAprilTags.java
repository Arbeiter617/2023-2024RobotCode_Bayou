package frc.robot.commands.limelightCommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class readAprilTags extends Command {
    public static double specificID;
    public static boolean isStage = false;
    public static boolean isAmp = false;
    public static boolean isSpeaker = false;

    public readAprilTags() {}

     @Override
    public void initialize() {}
     
     @Override
    public void execute() {
        specificID = limelightReadingTool.idRead;    

        //check tags//
        if(toggleLimelight.toggleLimelightPos) {
            limelightReadingTool.table.getEntry("ledMode").setNumber(0);
            if(isAmp) {
                RobotContainer.drivepls = true;
                RobotContainer.amp.execute();
            } else if(isSpeaker) {
                RobotContainer.drivepls = false;
                RobotContainer.speaker.execute();
            } else {
                RobotContainer.noneFound.execute();
            }
        } else {
            RobotContainer.drivepls = false;
            limelightReadingTool.table.getEntry("ledMode").setNumber(1);
        }

    }
}