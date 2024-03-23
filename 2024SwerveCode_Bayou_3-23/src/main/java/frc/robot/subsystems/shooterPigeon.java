

package frc.robot.subsystems;

import frc.robot.subsystems.SwerveModule;
import frc.robot.Constants;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.FollowPathHolonomic;
import com.pathplanner.lib.path.PathPlannerPath;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooterPigeon extends SubsystemBase {
    public Pigeon2 newGyro;
    public static double pitch;
    public static double yaw;
    public static double roll;

    public shooterPigeon() {
        newGyro = new Pigeon2(Constants.Swerve.pigeonIDShooter);
        newGyro.getConfigurator().apply(new Pigeon2Configuration());
        newGyro.setYaw(0);

    }

    @Override
    public void periodic(){
        roll = newGyro.getRoll().getValue();
        //SmartDashboard.putNumber("Shooter Roll", roll);

        pitch = newGyro.getPitch().getValue();
        //SmartDashboard.putNumber("Shooter Pitch", pitch);

        yaw = newGyro.getYaw().getValue();
        // SmartDashboard.putNumber("Shooter Yaw", yaw);
    }
}
