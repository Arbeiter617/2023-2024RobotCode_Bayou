

package frc.robot.subsystems;

import frc.robot.Constants;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;
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
        yaw = newGyro.getYaw().getValue();
    }
}
