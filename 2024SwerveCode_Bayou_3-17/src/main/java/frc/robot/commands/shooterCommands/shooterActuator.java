package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;
import frc.robot.subsystems.shooterPigeon;


public class shooterActuator extends Command {
    /*Calling Subsystems */
    shooterPigeon s_shooterPigeon;
    private shooterPigeon s_ShooterPigeon;

    //Offsets//
    double offsetVal;
    double highestShooterPoint;
    double lowestShooterPoint;
    double actuatorSpeed;

    /*Booleans */
    public static boolean canManuallyMove = true;

     public shooterActuator() {
        this.s_shooterPigeon = s_shooterPigeon;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        if(canManuallyMove) {
            if(RobotContainer.copilot.getRawAxis(controls.shooterActuatorAxis) < 0.0 - offsetVal && shooterPigeon.roll < highestShooterPoint) {
                //go up//
                runActuator(-actuatorSpeed);
            } else if(RobotContainer.copilot.getRawAxis(controls.shooterActuatorAxis) > 0.0 + offsetVal && shooterPigeon.roll > lowestShooterPoint) {
                //go down//
                runActuator(actuatorSpeed);
            } else {
                //stop//
                stopActuator();
            }
        }

    }

    public void runActuator(double speed) {
        Constants.wenchMotor.set(speed);
    }

    public void stopActuator() {
        Constants.wenchMotor.set(0);
    }
}
