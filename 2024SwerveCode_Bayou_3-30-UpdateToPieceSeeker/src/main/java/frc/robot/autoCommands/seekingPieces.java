package frc.robot.autoCommands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest.RobotCentric;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.automatedCommands.automatedIntake;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.subsystems.Swerve;

public class seekingPieces extends Command {
    double neededXValue = 0;
    double xOffset = 2;
    double alignSpeed = .15;
    private static Swerve s_Swerve;
    public static double transDouble = .5;
    public static double rotationDouble = .5;
    public static double strafeDouble = 0;

    public static boolean isAutoMove = false;

    boolean rotationAligned = false;
    public boolean transAligned = false;

    boolean gyroSaved = false;
    public static double savedGyroYaw;

    boolean gyroReset = false;

     public seekingPieces() {
        this.s_Swerve = s_Swerve;
     }
   
     @Override
     public void initialize() {
     }
   
     @Override
     public void execute() {
        //System.out.println("Running move");
        isAutoMove = true;
        TeleopSwerve.calledDuringAutotest();
        automatedIntake.intakeDownCommand();

        if(limelightReadingTool.xValuePD > (neededXValue + xOffset) && !colorSensorRun.pieceIsFound) {
            //move left//
            rotationDouble = -alignSpeed;
        } else if(limelightReadingTool.xValuePD < (neededXValue - xOffset) && !colorSensorRun.pieceIsFound) {
            //move right//
            rotationDouble = alignSpeed;
        } else {
            //stop//
            rotationDouble = 0;
            rotationAligned = true;

            //zero gyro//
            if(!gyroReset) {
                System.out.println("Gyro reset pos");
                TeleopSwerve.zeroAutoGyro();
                gyroReset = true;
            } 
            //intake down//
           
        }

        if(rotationAligned && !colorSensorRun.pieceIsFound) {
            //move forward//
            intakeIntake.runIntakeSpeed(intakeIntake.highestIntakeSpeed);
            if(!gyroSaved) {
                System.out.println("Gyro Data Saved");
                TeleopSwerve.saveGyroData();
                gyroSaved = true;
            }
            
            transDouble = .25;
        } else {
            transDouble = 0;
        }

        //check for piece//
        if(colorSensorRun.pieceIsFound) {
            intakeIntake.runIntakeSpeed(0);
            //intake up//
            automatedIntake.intakeUpCommand();
            transAligned = true;
        }
        
        
    }


    public boolean isFinished() {
        return transAligned;
    }

    public void end() {

    }

}
