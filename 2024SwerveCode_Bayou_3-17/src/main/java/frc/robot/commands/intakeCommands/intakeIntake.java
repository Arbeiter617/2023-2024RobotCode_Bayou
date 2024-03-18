package frc.robot.commands.intakeCommands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.controls.controls;

public class intakeIntake extends Command {
    /*Editable Values */
    double highestIntakeSpeed = 1;
    double highestOutakeSpeed = -1;


    /*Booleans */
    boolean canManuallyIntake = true;
     public intakeIntake() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        
        if(canManuallyIntake) {
            if(!colorSensorRun.pieceIsFound) {
                //doesnt have a piece//
                if(RobotContainer.copilot.getRawButton(controls.intakeButton)) {
                    //intake in//
                    runIntakeSpeed(highestIntakeSpeed);
                } else if(RobotContainer.copilot.getRawButton(controls.outakeButton)) {
                    //intake out//
                    runIntakeSpeed(highestOutakeSpeed);
                } else {
                    //stop//
                    stopintakeSpeed();
                }
            } else {
                //has a piece//
                if(RobotContainer.copilot.getRawButton(controls.intakeButton)) {
                    //intake in//
                    runIntakeSpeed(highestIntakeSpeed/2);
                } else if(RobotContainer.copilot.getRawButton(controls.outakeButton)) {
                    //intake out//
                    runIntakeSpeed(highestOutakeSpeed);
                } else {
                    //stop//
                    stopintakeSpeed();
                }
            }
        } 
    }

    public void runIntakeSpeed(double speed) {
        Constants.intakeMotorIn.set(speed);
    }

    public void stopintakeSpeed() {
        Constants.intakeMotorIn.set(0);
    }
}
