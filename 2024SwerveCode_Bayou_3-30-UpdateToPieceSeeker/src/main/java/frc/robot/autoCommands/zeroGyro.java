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

public class zeroGyro extends Command {
    public static double newGyroVal;
    boolean reset = false;
     public zeroGyro() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(seekingPieces.savedGyroYaw > 180) {
            //turned left//
            System.out.println("LEFT");
            TeleopSwerve.resetGyro();
            reset = true;
        } else if(seekingPieces.savedGyroYaw < 180) {
            System.out.println("RIGHT");
            TeleopSwerve.resetGyro();
            reset = true;
            //turned right//
        } else {
            System.out.println("ERROR");
            reset = false;
            return;
        }
        
    }

    public boolean isFinished() {
        return reset;
    }

    public void end() {

    }

}
