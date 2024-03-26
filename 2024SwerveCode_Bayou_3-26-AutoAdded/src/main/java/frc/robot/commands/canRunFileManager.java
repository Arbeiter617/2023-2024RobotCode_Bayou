package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.readAprilTags;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;

public class canRunFileManager extends Command {

     public canRunFileManager() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(readAprilTags.isSpeaker || readAprilTags.isStage) {
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