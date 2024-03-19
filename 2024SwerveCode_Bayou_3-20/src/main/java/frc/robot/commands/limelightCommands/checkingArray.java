package frc.robot.commands.limelightCommands;

import edu.wpi.first.wpilibj2.command.Command;

public class checkingArray extends Command {
    double[] stageArray = {15, 16, 14, 12, 11, 13};
    double[] speakerArray = {7, 4, 3, 8};
    double[] ampArray = {6, 5};

     public checkingArray() {}
   
     @Override
     public void initialize() {
        for(int i = 0; i < speakerArray.length; i++) {
            if(speakerArray[i] == readAprilTags.specificID) {
                readAprilTags.isSpeaker = true;
                 readAprilTags.isStage = false;
                 readAprilTags.isAmp = false;
            } 
        }

        for(int a = 0; a < ampArray.length; a++) {
            if(ampArray[a] == readAprilTags.specificID) {
                readAprilTags.isAmp = true;
                 readAprilTags.isStage = false;
                 readAprilTags.isSpeaker = false;
            } 
        }

        for(int b = 0; b < stageArray.length; b++) {
            if(stageArray[b] == readAprilTags.specificID) {
                readAprilTags.isStage = true;
                readAprilTags.isSpeaker = false;
                readAprilTags.isAmp = false;
            } 
        }
     }
   
}