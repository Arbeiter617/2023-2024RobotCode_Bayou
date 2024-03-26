package frc.lib.config;

//import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
//import com.ctre.phoenix6.sensors.SensorInitializationStrategy;
//import com.ctre.phoenix.sensors.SensorTimeBase;
import frc.robot.Constants;

public final class CTREConfigs {
  public CANcoderConfiguration swerveCANcoderConfig = new CANcoderConfiguration();
  public CTREConfigs(){
    swerveCANcoderConfig = new CANcoderConfiguration();

  //swerveCANcoderConfig.MagnetSensor.SensorDirection = Constants.Swerve.cancoderInvert;
  // public CANCoderConfiguration swerveCanCoderConfig;

  // public CTREConfigs() {
  //   swerveCanCoderConfig = new CANCoderConfiguration();

  //   /* Swerve CANCoder Configuration */
  //   swerveCanCoderConfig.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
  //   swerveCanCoderConfig.sensorDirection = Constants.Swerve.canCoderInvert;
  //   swerveCanCoderConfig.initializationStrategy =
  //       SensorInitializationStrategy.BootToAbsolutePosition;
  //   swerveCanCoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
  // }
}
}
