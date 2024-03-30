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
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.subsystems.Swerve;

public class speakerFinder extends Command {
    double neededXValue = 0;
    double neededYValue = -23;
    double xOffset = 2;
    double yOffset = 2;
    double alignSpeed = .15;
    private static Swerve s_Swerve;
    public static double transDouble = 0;
    public static double rotationDouble = 0;
    public static double strafeDouble = 0;

    public static boolean isAutoMove = false;

    boolean zeroGyro = false;

    boolean xAligned = false;
    public boolean pieceShot = false;
    boolean setTime = false;
    double time;

     public speakerFinder() {
        this.s_Swerve = s_Swerve;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        //System.out.println("Running move");
        isAutoMove = true;
        TeleopSwerve.calledDuringAutotest2();

        shooterShoot.runShooter(1);

        //System.out.println(limelightReadingTool.xValue);

        if(limelightReadingTool.idRead == 4 || limelightReadingTool.idRead == 7) {
            if(limelightReadingTool.xValue > (neededXValue + xOffset)) {
            //move left//
            rotationDouble = -alignSpeed;
            System.out.println("Rotating");
        } else if(limelightReadingTool.xValue < (neededXValue - xOffset)) {
            //move right//
            rotationDouble = alignSpeed;
            //System.out.println("Rotating");
        } else {
            //stop//
            //System.out.println("ALIGNED");
            rotationDouble = 0;
            xAligned = true;

            
        }
        } else {
            //spin//
            if(colorSensorRun.pieceIsFound) {
                rotationDouble = alignSpeed;
                //System.out.println("Rotating");
            }
        }

        if(xAligned && colorSensorRun.pieceIsFound) {
            if(limelightReadingTool.yValue > (neededYValue + yOffset)) {
                shooterActuator.runActuator(-1);
                } else if(limelightReadingTool.yValue < (neededYValue - yOffset)) {
                shooterActuator.runActuator(1);
            } else {
                shooterActuator.stopActuator();
                //run outake//
                intakeIntake.runIntakeSpeed(intakeIntake.highestOutakeSpeed);
            }
        }

        //check for piece//
        if(!colorSensorRun.pieceIsFound) {
            shooterActuator.stopActuator();
            rotationDouble = 0;
            if(!setTime) {
                time = System.currentTimeMillis();
                setTime = true;
            }
            if(System.currentTimeMillis() > time + 500) {
                
            intakeIntake.stopintakeSpeed();
            pieceShot = true;
            }
            
        }
        
        
    }

    public boolean isFinished() {
        return pieceShot;
    }

    public void end() {

    }

}
