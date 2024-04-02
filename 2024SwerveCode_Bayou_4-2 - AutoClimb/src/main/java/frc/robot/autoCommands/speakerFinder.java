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
    double neededXValue2 = 5;
    double neededYValue = -22;
    double xOffset = 3;
    double yOffset = 2;
    double alignSpeed = .15;
    private static Swerve s_Swerve;
    public static double transDouble = 0;
    public static double rotationDouble = 0;
    public static double strafeDouble = 0;

    public static boolean isAutoMove = false;

    boolean zeroGyro = false;

    public static boolean xAligned = false;
    public static boolean pieceShot = false;
    static boolean setTime = false;
    double time;
    public static boolean stop = false;

    int yawOffset = 2;

     public speakerFinder() {
        this.s_Swerve = s_Swerve;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        isAutoMove = true;
        TeleopSwerve.calledDuringAutotest2();
        shooterShoot.runShooter(1);

        if(limelightReadingTool.idRead == 4 || limelightReadingTool.idRead == 7) {
            if(limelightReadingTool.xValue > (neededXValue + xOffset)) {
            //move left//
            rotationDouble = -alignSpeed * 2;
        } else if(limelightReadingTool.xValue < (neededXValue - xOffset)) {
            //move right//
            rotationDouble = alignSpeed * 2;
        } else {
            //stop//
            if(!pieceShot) {
                System.out.println("ALIGNED");
                rotationDouble = 0;
            }
            xAligned = true; 
        }
        } else {
            //spin//
            if(colorSensorRun.pieceIsFound) {
                if(TeleopSwerve.yawValue > 180 + yawOffset) {
                    rotationDouble = -.15;
                } else if(TeleopSwerve.yawValue < 180 - yawOffset) {
                    rotationDouble = .15;
                }
            }
        }

        if(xAligned && colorSensorRun.pieceIsFound) {
            if(limelightReadingTool.yValue > (neededYValue + yOffset)) {
                shooterActuator.runActuator(-.75);
                } else if(limelightReadingTool.yValue < (neededYValue - yOffset)) {
                shooterActuator.runActuator(.75);
            } else {
                shooterActuator.stopActuator();
                //run outake//
                intakeIntake.runIntakeSpeed(intakeIntake.highestOutakeSpeed);
            }
        }

        //check for piece//
        if(!colorSensorRun.pieceIsFound) {
            System.out.println("PIECE SHOT!");
            shooterActuator.stopActuator();
            if(!setTime) {
                time = System.currentTimeMillis();
                setTime = true;
            }
            if(System.currentTimeMillis() > time + 500) {
                
            intakeIntake.stopintakeSpeed();
            shooterShoot.stopShooter();
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
