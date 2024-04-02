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

public class intakeUp extends Command {
    boolean intakeUp = false;
     public intakeUp() {
       
     }
   
     @Override
     public void initialize() {
     }
   
     @Override
     public void execute() {
        if(intakeChain.encoderVal < intakeChain.highestIntakePoint) {
            intakeUp = false;
            System.out.println("GOING UP");
            if(intakeChain.encoderVal > -10) {
                intakeChain.runIntake((intakeChain.upSpeed) / 2);
            } else {
                intakeChain.runIntake(intakeChain.upSpeed);
            }
        } else {
            //at highest point... turn off command//
            intakeChain.stopIntake();
            //Constants.intakeMotorUp.getEncoder().setPosition(0);
            intakeUp = true;
            System.out.println("UP");
        }
    }
    
    public boolean isFinished() {
        return intakeUp;
    }

    public void end() {

    }

}
