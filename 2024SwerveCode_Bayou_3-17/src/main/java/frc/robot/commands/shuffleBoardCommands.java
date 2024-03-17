package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.subsystems.shooterPigeon;


public class shuffleBoardCommands extends Command {
    /*Calling Subsystems */
    shooterPigeon s_shooterPigeon;
    private shooterPigeon s_ShooterPigeon;

     public shuffleBoardCommands() {
        this.s_shooterPigeon = s_shooterPigeon;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        /*Put all needed values on to shufflboard HERE */
        SmartDashboard.putNumber("Shooter Pigeon Value", shooterPigeon.roll);
        SmartDashboard.putNumber("Shooter Speed", shooterShoot.actualSpeed);
        SmartDashboard.putNumber("Intake Chain Encoder", intakeChain.encoderVal);
    }
}
