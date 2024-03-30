package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.automatedCommands.automatedIntake;
import frc.robot.commands.automatedCommands.automatedShooting;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.controls.controls;
import frc.robot.controls.gunControls;

public class intakeIntake extends Command {
    /*Editable Values */
    public static double highestIntakeSpeed = .8;
    public static double highestOutakeSpeed = -1;


    /*Booleans */
    public static boolean canManuallyIntake = true;
     public intakeIntake() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        
        if(canManuallyIntake) {
            if(!automatedShooting.automatedShooting && !automatedIntake.automatedIntake) {
                if(!colorSensorRun.pieceIsFound) {
                    //doesnt have a piece//
                    if(RobotContainer.copilot_Xbox.getRawButton(gunControls.intakeButton)) {
                        //intake in//
                        runIntakeSpeed(highestIntakeSpeed);
                    } else if(RobotContainer.copilot_arduino.getRawAxis(gunControls.outakeAxis) < -.5) {
                        //intake out//
                        if(RobotContainer.copilot_flightStick.getRawAxis(gunControls.safetyAxis) < -.1) {
                            runIntakeSpeed(highestOutakeSpeed);
                        }
                        
                    } else {
                        //stop//
                        stopintakeSpeed();
                    }
                } else {
                    //has a piece//
                    if(RobotContainer.copilot_Xbox.getRawButton(gunControls.intakeButton)) {
                        //intake in//
                        runIntakeSpeed(highestIntakeSpeed/4);
                    } else if(RobotContainer.copilot_arduino.getRawAxis(gunControls.outakeAxis) < -.5) {
                        //intake out//
                        //if(RobotContainer.copilot_flightStick.getRawAxis(gunControls.safetyAxis) < -.1) {
                            runIntakeSpeed(highestOutakeSpeed);
                        //}
                    } else {
                        //stop//
                        stopintakeSpeed();
                    }
              }
            }
        } 
    }

    public static void runIntakeSpeed(double speed) {
        Constants.intakeMotorIn.set(speed);
    }

    public static void stopintakeSpeed() {
        Constants.intakeMotorIn.set(0);
    }
}
