package frc.robot.commands.artificialLearningTools;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
import frc.robot.controls.controls;

public class accuracyLogger extends Command {
    public static int timesTooHigh = 0;
    public static int timesTooLow = 0;
    int timeInterval = 100;
    double timeClicked;
    public static boolean hasPressed = false;

    int correctionInterval = 1;
     public accuracyLogger() {
     }
   
     @Override
     public void initialize() {
        timesTooHigh = 0;
        timesTooLow = 0;
     }
   
     @Override
     public void execute() {
        if(RobotContainer.copilot.getRawButton(controls.tooHighMiss) && !hasPressed) {
            //log that u missed too high//
            resetPress();
            timeClicked = System.currentTimeMillis();
            System.out.println(timeClicked);
            takeSnapshot();
            tooHigh();
            timesTooHigh = timesTooHigh + 1;
        } else if(RobotContainer.copilot.getRawButton(controls.tooLowMiss) && !hasPressed) {
            //log that u missed too low//
            timeClicked = System.currentTimeMillis();
            System.out.println(timeClicked);
            timesTooLow = timesTooLow + 1;
            takeSnapshot();
            tooLow();
            resetPress();
        } else {
            //nothing//
            timeClicked = 0;
            resetSnapshot();
        }

        checkTimePressed();
    }

    void takeSnapshot() {
        limelightReadingTool.table.getEntry("snapshot").setNumber(1);
    }

    void resetSnapshot() {
        limelightReadingTool.table.getEntry("snapshot").setNumber(0);
    }

    void resetPress() {
        hasPressed = true;
    }

    void checkTimePressed() {
        if(System.currentTimeMillis() >= timeClicked + timeClicked) {
            hasPressed = false;
        } 
    }

    void tooHigh() {
        speaker.centerYValue = speaker.centerYValue + correctionInterval;
    }

    void tooLow() {
        speaker.centerYValue = speaker.centerYValue - correctionInterval;
    }

}
