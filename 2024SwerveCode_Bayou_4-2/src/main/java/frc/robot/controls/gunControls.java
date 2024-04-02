package frc.robot.controls;
import edu.wpi.first.wpilibj2.command.Command;

public class gunControls extends Command {
        //buttons on flight stick//
        public static int automatedShootingButton = 1;
        public static int automatedIntakeButton = 3;
        public static int limelightPOV = 0;

            //axis//
            public static int safetyAxis = 1;
        
        //arduino//
        public static int shooterActuatorAxis = 0;
        public static int outakeAxis = 1;

        //xbox//
        public static int intakeChainButtonUp = 3;
        public static int intakeChainButtonDown = 4;
        public static int ampButton = 5;
        public static int intakeButton = 6;
        public static int resetEncoder = 7;



     public gunControls() {
        
     }

}
