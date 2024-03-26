package frc.robot.commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest.RobotCentric;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.ampBarCommands.ampAssistControl;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.readAprilTags;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;
import frc.robot.subsystems.shooterPigeon;

public class ampControl extends Command {
    public static boolean isAmp = false;
     public ampControl() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.copilot.getRawButton(controls.ampButton)) {
            //check for amp//
            ampAssistControl.canManuallyMove = false;
            

                RobotContainer.amp.execute();
            
        } else {
            if(!ampAssistControl.canManuallyMove) {
                if(ampAssistControl.encoderVal > ampAssistControl.lowestPosition) {
                    //go down//
                    
                    //System.out.println("?");
                    ampAssistControl.runAmpThing(ampAssistControl.actualSpeed * -1);
                } else {
                    //stop//
                    ampAssistControl.stopAmpThing();
                    ampAssistControl.canManuallyMove = true;
                
            }
        }
        }
      
    }
}