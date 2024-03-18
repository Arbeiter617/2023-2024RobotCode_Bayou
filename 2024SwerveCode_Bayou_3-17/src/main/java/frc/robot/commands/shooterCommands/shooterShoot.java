package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;

public class shooterShoot extends Command {
    /*Editable Values */
    double highestShooterSpeed = 1;
    double lowestShooterSpeed = 0;
    public static double actualSpeed;

    /*Booleans */
    public static boolean canManuallyShoot = true;
     public shooterShoot() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        actualSpeed = RobotContainer.copilot.getRawAxis(controls.shooterControlAxis);
        
        if(canManuallyShoot) {
            if(RobotContainer.driver.getRawAxis(controls.shooterControlAxis) > 0.0) {
                if(actualSpeed <= highestShooterSpeed && actualSpeed >= lowestShooterSpeed) {
                    runShooter(actualSpeed);
                    runShooter(actualSpeed);
                } else {
                    stopShooter();
                }
            } else {
                stopShooter();
            }
        }

    }

    public static void runShooter(double speed) {
        Constants.shooterLeft.set(speed);
        Constants.shooterRight.set(-speed);
    }

    public static void stopShooter() {
        Constants.shooterLeft.set(0);
        Constants.shooterRight.set(0);
    }
}
