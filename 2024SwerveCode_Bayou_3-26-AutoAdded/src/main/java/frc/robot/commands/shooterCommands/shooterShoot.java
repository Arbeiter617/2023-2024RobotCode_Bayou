package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.automatedCommands.automatedShooting;
import frc.robot.controls.controls;

public class shooterShoot extends Command {
    /*Editable Values */
    double highestShooterSpeed = 1;
    double lowestShooterSpeed = 0;
    public static double actualSpeed = 0;

    /*Booleans */
    public static boolean canManuallyShoot = true;
     public shooterShoot() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        actualSpeed = RobotContainer.copilot.getRawAxis(controls.shooterControlAxis);

        if(canManuallyShoot && !automatedShooting.automatedShooting) {
            if(RobotContainer.copilot.getRawAxis(controls.shooterControlAxis) > 0.0) {
                runShooter(actualSpeed);
            } else {
                stopShooter();
            }
        }

    }

    public static void runShooter(double speed) {
        Constants.shooterLeft.set(-speed);
        Constants.shooterRight.set(speed);
    }

    public static void stopShooter() {
        Constants.shooterLeft.set(0);
        Constants.shooterRight.set(0);
    }
}
