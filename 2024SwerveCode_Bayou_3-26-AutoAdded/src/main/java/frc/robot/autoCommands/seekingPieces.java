package frc.robot.autoCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.subsystems.Swerve;

public class seekingPieces extends Command {
    double neededXValue = 0;
    double xOffset = 2;
    double alignSpeed = .15;

    public static double transDouble;
    public static double rotationDouble;
    public static double strafeDouble;
     public seekingPieces() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        TeleopSwerve.calledDuringAutotest();
    }

}
