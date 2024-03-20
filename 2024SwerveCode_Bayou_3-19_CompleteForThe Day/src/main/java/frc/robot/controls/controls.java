package frc.robot.controls;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;

public class controls extends Command {
    /*Pilot Controls */
        //climbers//
        public static int climberLeftUpAxis = 2;
        public static int climberRightUpAxis = 3;
        public static int climberLeftDownButton= 5;
        public static int climberRightDownButton = 6;
    /*Copilot controls */
        //shooter//
        public static int shooterActuatorAxis = 1;
        public static int shooterControlAxis = 2;
        public static int outakeAxis = 3;
        //intake//
        public static int intakeChainControlAxis = 5;
        public static int intakeButton = 5;
        //limelight toggle//
        public static int liemlightToggleButton = 3;
        //miss buttons//
        public static int tooHighMiss = 4;
        public static int tooLowMiss = 1;

        public static int resetEncoder = 8;

     public controls() {
        
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
       
    }

}
