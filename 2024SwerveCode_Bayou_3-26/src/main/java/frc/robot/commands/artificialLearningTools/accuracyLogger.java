package frc.robot.commands.artificialLearningTools;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
import frc.robot.controls.controls;

public class accuracyLogger extends Command {
    boolean hasCalled = false;

    int correctionInterval = 1;
     public accuracyLogger() {
     }
   
     @Override
     public void initialize() {
     }
   
     @Override
     public void execute() {
       if(RobotContainer.copilot.getRawButton(controls.tooHighMissPOV) && !hasCalled) {
            //missed too high..//
            hasCalled = true;
            tooHigh();
            takeSnapshot();
       } else if(RobotContainer.copilot.getRawButton(controls.tooLowMissPOV) && !hasCalled) {
            //missed too low..//
            hasCalled = true;
            tooLow();
            takeSnapshot();
       } else {
            resetSnapshot();
       }
    }

    void takeSnapshot() {
        limelightReadingTool.table.getEntry("snapshot").setNumber(1);
    }

    void resetSnapshot() {
        limelightReadingTool.table.getEntry("snapshot").setNumber(0);
    }

    void tooHigh() {
        if(hasCalled) {
            speaker.centerYValue = speaker.centerYValue + correctionInterval;
            hasCalled = false;
        }
    }

    void tooLow() {
        if(hasCalled) {
        speaker.centerYValue = speaker.centerYValue - correctionInterval;
        hasCalled = false;
        }
    }

}
