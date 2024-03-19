package frc.robot.commands.artificialLearningTools;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;

public class accuracyLogger extends Command {
    public static int timesTooHigh = 0;
    public static int timesTooLow = 0;
    int timeInterval = 100;
    double timeClicked;
    public static boolean hasPressed = false;
    boolean hasPiece = false;

    public static int piecesShot = 0;

    int correctionInterval;
     public accuracyLogger() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(RobotContainer.copilot.getRawButton(controls.tooHighMiss) && !hasPressed) {
            //log that u missed too high//
            timeClicked = System.currentTimeMillis();
            System.out.println(timeClicked);
            timesTooHigh = timesTooHigh + 1;
            takeSnapshot();
            tooHigh();
            resetPress();
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
        checkIfPieceWasShot();
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

    void checkIfPieceWasShot() {
        //check number of shots registered//
        if(colorSensorRun.pieceIsFound) {
            hasPiece = true;
        }

        if(hasPiece && !colorSensorRun.pieceIsFound) {
            //had piece, but now its gone//
            System.out.println("Piece has been shot");
            piecesShot += 1;
            hasPiece = false;
        }
    }

}
