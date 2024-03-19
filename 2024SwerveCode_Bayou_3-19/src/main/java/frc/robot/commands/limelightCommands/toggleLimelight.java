package frc.robot.commands.limelightCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;

public class toggleLimelight extends Command {

    public static Boolean toggleLimelightPos = false;
    Boolean togglePos = true;

     public toggleLimelight() {
        
     }
   
    
     @Override
     public void initialize() {}
   
     
     @Override
     public void execute() {
            if (togglePos && RobotContainer.copilot.getRawButton(controls.liemlightToggleButton)) { 
            togglePos = false;  
              if (toggleLimelightPos) { 
                reset();
               toggleLimelightPos = false;
            } else {
              toggleLimelightPos = true;
            }
            } else if(RobotContainer.copilot.getRawButton(controls.liemlightToggleButton) == false) { 
               togglePos = true; 
           }  
    }

    void reset() {
      readAprilTags.isSpeaker = false;
      readAprilTags.isStage = false;
      readAprilTags.isAmp = false;
    }
}