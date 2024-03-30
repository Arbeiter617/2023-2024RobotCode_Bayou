package frc.robot.commands.limelightCommands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
import frc.robot.controls.controls;
import frc.robot.controls.gunControls;

public class toggleLimelight extends Command {

    public static Boolean toggleLimelightPos = false;
    Boolean togglePos = true;


     public toggleLimelight() {
        
     }
   
    
     @Override
     public void initialize() {}
   
     
     @Override
     public void execute() {
            if (togglePos && RobotContainer.copilot_flightStick.getPOV(gunControls.limelightPOV) == 0) { 
            togglePos = false;  
              if (toggleLimelightPos) { 
                reset();
               toggleLimelightPos = false;
            } else {
              toggleLimelightPos = true;
            }
            } else if(RobotContainer.copilot_flightStick.getPOV(gunControls.limelightPOV) != 0) { 
               togglePos = true; 
           }  
    }

    void reset() {
      readAprilTags.isSpeaker = false;
      readAprilTags.isStage = false;
      speaker.timeSet = false;
    }
}