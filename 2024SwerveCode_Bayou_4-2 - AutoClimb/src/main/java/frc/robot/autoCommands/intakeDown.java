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

public class intakeDown extends Command {
    boolean intakeDown = false;
     public intakeDown() {
       
     }
   
     @Override
     public void initialize() {
     }
   
     @Override
     public void execute() {
        System.out.println(intakeChain.encoderVal + " : " + intakeChain.lowestIntakePoint);
        if(intakeChain.encoderVal > -6) {
            intakeDown = false;
            intakeChain.runIntake(-.25);
        } else {
            intakeDown = true;
            intakeChain.stopIntake();
        }
    }
    
    public boolean isFinished() {
        return intakeDown;
    }

    public void end() {

    }

}
