package frc.robot.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.controls.controls;
import frc.robot.controls.gunControls;
import frc.robot.subsystems.shooterPigeon;


public class shooterActuatorA extends Command {
    /*Calling Subsystems */
    shooterPigeon s_shooterPigeon;
    private shooterPigeon s_ShooterPigeon;

    //Offsets//
    double offsetVal = .25;
    double highestShooterPoint = 60;
    double lowestShooterPoint = 25;
    double actuatorSpeed = 1;

    /*Booleans */
    public static boolean isDown = true;
    boolean direction;

     public shooterActuatorA(boolean direction) {
        this.s_shooterPigeon = s_shooterPigeon;
        this.direction = direction;
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
            if(shooterPigeon.roll < highestShooterPoint && direction) {
                //go up//\
                    //safety//
                    System.out.println("UP");
                    isDown = false;
                    runActuator(-actuatorSpeed);
                
            } else if(shooterPigeon.roll > lowestShooterPoint && !direction) {
                //go down//
                    //safety//
                    System.out.println("DOWN");
                    isDown = false;
                    runActuator(actuatorSpeed);
                
            } else {
                //stop//
                stopActuator();
                isDown = true;
            }

    }

    public static void runActuator(double speed) {
        Constants.wenchMotor.set(speed);
    }

    public static void stopActuator() {
        Constants.wenchMotor.set(0);
    }

    public boolean isFinished() {
        return isDown;
    }

    public void end() {

    }
}
