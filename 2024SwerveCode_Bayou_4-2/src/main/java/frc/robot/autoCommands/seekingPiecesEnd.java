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
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.limelightAutoCommands.speaker;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.subsystems.Swerve;

public class seekingPiecesEnd extends Command {
    double neededXValue = 0;
    double xOffset = 3;
    double alignSpeed = .25;
    private static Swerve s_Swerve;
    public static double transDouble = 0;
    public static double rotationDouble = 0;
    public static double strafeDouble = 0;

    public static boolean isAutoMove = false;

    boolean rotationAligned = false;
    public static boolean transAligned = false;

    boolean gyroSaved = false;
    public static double savedGyroYaw;

    boolean gyroReset = false;
    public static boolean resetEncoder = false;

    int yawOffset = 2;
    boolean stop = false;

     public seekingPiecesEnd() {
        this.s_Swerve = s_Swerve;
     }
   
     @Override
     public void initialize() {
     }
   
     @Override
     public void execute() {
        TeleopSwerve.getYaw();
        isAutoMove = true;
        TeleopSwerve.calledDuringAutotest();

        if(!resetEncoder) {
            shooterActuatorA.isAtPos = false;
            speakerFinder.xAligned = false;
            speakerFinder.stop = false;
            speakerFinder.pieceShot = false;
            resetEncoder = true;
            rotationAligned = false;
            transAligned = false;
            stop = false;
            System.out.println("RESET ALL BOOLEANS");
        }

        shooterActuator.runActuator(.5);

        System.out.println(TeleopSwerve.yawValue);
        //reset rotation//

   
            if(limelightReadingTool.xValuePD > (neededXValue + xOffset) && !colorSensorRun.pieceIsFound) {
                //move left//
                strafeDouble = -alignSpeed;
                //rotationDouble = .15;
                //rotationDouble = -alignSpeed;
            } else if(limelightReadingTool.xValuePD < (neededXValue - xOffset) && !colorSensorRun.pieceIsFound) {
                //move right//
                strafeDouble = alignSpeed;
                //rotationDouble = -.15;
                //rotationDouble = alignSpeed;
            } else {
                //stop//
                strafeDouble = 0;
                //rotationDouble = 0;
                rotationAligned = true;
           
            }
        
        //check for piece//
        if(colorSensorRun.pieceIsFound) {
            intakeIntake.runIntakeSpeed(0);
            //intake up//
            automatedIntake.intakeUpCommand();
            transDouble = 0;
            transAligned = true;
            resetEncoder = false;
        }

        if(!colorSensorRun.pieceIsFound && rotationAligned) {
            //move forward//
            intakeIntake.runIntakeSpeed(1);     
            transDouble = .35;
        } else {
            transDouble = 0;
        }
    }
    
    public boolean isFinished() {
        return transAligned;
    }

    public void end() {

    }

}
