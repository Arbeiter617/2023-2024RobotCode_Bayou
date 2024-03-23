package frc.robot.commands.ampBarCommands;

import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;

public class ampAssistControl extends Command {
    /*Editable Values */
    public static double lowestPosition = 0;
    public static double highestPosition = 7;
    public static double actualSpeed = .25;

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
        actualSpeed = .1;
        encoderVal = Constants.ampAssist.getEncoder().getPosition();

        if(canManuallyMove) {
            if(RobotContainer.copilot.getPOV() == controls.barPOVUp&& encoderVal < highestPosition) {
                //go up//
                runAmpThing(actualSpeed);
            } else if(RobotContainer.copilot.getPOV() == controls.barPOVDown && encoderVal > lowestPosition) {
                //go down//
                System.out.println("?");
                runAmpThing(actualSpeed * -1);
            } else {
                //stop//
                stopAmpThing();
            }
        }

        if(RobotContainer.copilot.getRawButton(7)) {
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
