package frc.robot.commands.ampBarCommands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;
import frc.robot.controls.gunControls;

public class ampAssistControl extends Command {
    /*Editable Values */
    public static double lowestPosition = 0;
    public static double highestPosition = 7;
    public static double actualSpeed = .1;

    /*Encoder Val */
    public static double encoderVal;
    /*Booleans */
    public static boolean canManuallyMove = true;
     public ampAssistControl() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        encoderVal = Constants.ampAssist.getEncoder().getPosition();
        
        if(RobotContainer.copilot_Xbox.getRawButton(gunControls.resetEncoder)) {
            //rest encoder//
            Constants.ampAssist.getEncoder().setPosition(0);
        }
    }
    
    public static void runAmpThing(double speed) {
        Constants.ampAssist.set(speed);
    }
       
    public static void stopAmpThing() {
        Constants.ampAssist.set(0);
    }

}
