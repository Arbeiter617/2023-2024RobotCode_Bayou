package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.ampBarCommands.ampAssistControl;
import frc.robot.controls.gunControls;

public class ampControl extends Command {
    public static boolean isAmp = false;
     public ampControl() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.copilot_Xbox.getRawButton(gunControls.ampButton)) {
            //check for amp//
            ampAssistControl.canManuallyMove = false;
            RobotContainer.amp.execute();
            
        } else {
            if(!ampAssistControl.canManuallyMove) {
                if(ampAssistControl.encoderVal > ampAssistControl.lowestPosition) {
                    //go down//
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