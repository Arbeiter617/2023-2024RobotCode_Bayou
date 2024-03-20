package frc.robot.commands.limelightCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.subsystems.shooterPigeon;

public class canRunFileManager extends Command {

     public canRunFileManager() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(readAprilTags.isAmp || readAprilTags.isSpeaker || readAprilTags.isStage) {
            shooterActuator.canManuallyMove = false;
            shooterShoot.canManuallyShoot = false;
            intakeChain.canManuallyMove = false;
            intakeIntake.canManuallyIntake = false;
        } else {
            shooterActuator.canManuallyMove = true;
            shooterShoot.canManuallyShoot = true;
            intakeChain.canManuallyMove = true;
            intakeIntake.canManuallyIntake = true;
        }
      
    }
}