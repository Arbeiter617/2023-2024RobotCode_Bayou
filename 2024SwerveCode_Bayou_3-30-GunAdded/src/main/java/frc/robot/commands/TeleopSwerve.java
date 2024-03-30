package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.autoCommands.seekingPieces;
import frc.robot.autoCommands.speakerFinder;
import frc.robot.subsystems.Swerve;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class TeleopSwerve extends Command {
  private static Swerve s_Swerve;
  private DoubleSupplier translationSup;
  private DoubleSupplier strafeSup;
  private DoubleSupplier rotationSup;
  private static BooleanSupplier robotCentricSup;

  private SlewRateLimiter translationLimiter = new SlewRateLimiter(3.0);
  private SlewRateLimiter strafeLimiter = new SlewRateLimiter(3.0);
  private SlewRateLimiter rotationLimiter = new SlewRateLimiter(3.0);

  public static double translationVal;
  public static double strafeVal;
  public static double rotationVal;

  public static double movementSpeedAutoTransform;
  public static double movementSpeedAutoRotation;
  public static double movementSpeedAutoStrafe;

  public TeleopSwerve(
      Swerve s_Swerve,
      DoubleSupplier translationSup,
      DoubleSupplier strafeSup,
      DoubleSupplier rotationSup,
      BooleanSupplier robotCentricSup) {
    this.s_Swerve = s_Swerve;
    addRequirements(s_Swerve);

    this.translationSup = translationSup;
    this.strafeSup = strafeSup;
    this.rotationSup = rotationSup;
    this.robotCentricSup = robotCentricSup;
  }

  @Override
  public void execute() {
    /* Get Values, Deadband*/
    if(RobotContainer.drivepls) {
      translationVal =
        translationLimiter.calculate(
            MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.Swerve.stickDeadband));
      rotationVal = movementSpeedAutoRotation;
      strafeVal = movementSpeedAutoStrafe;
    } else if(RobotContainer.rotateToPiece) {
      translationVal =
        translationLimiter.calculate(
            MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.Swerve.stickDeadband));
      rotationVal = movementSpeedAutoRotation;
      strafeVal = strafeLimiter.calculate(
            MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.Swerve.stickDeadband));
    } else {
     translationVal =
        translationLimiter.calculate(
            MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.Swerve.stickDeadband));
     strafeVal =
        strafeLimiter.calculate(
            MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.Swerve.stickDeadband));
     rotationVal =
        rotationLimiter.calculate(
            MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.Swerve.stickDeadband));
    }
    /* Drive */
    if(RobotContainer.driver.getRawButton(1)) {
      s_Swerve.drive(
        new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed * 4),
        -rotationVal * Constants.Swerve.maxAngularVelocity * 4,
        !robotCentricSup.getAsBoolean(),
        true);
    } else if(RobotContainer.driver.getRawButton(2)) {
      s_Swerve.drive(
        new Translation2d(translationVal / 2, strafeVal / 2).times(Constants.Swerve.maxSpeed / 2),
        -rotationVal * Constants.Swerve.maxAngularVelocity / 2,
        !robotCentricSup.getAsBoolean(),
        true);
    }else {
      if(!seekingPieces.isAutoMove) {
        s_Swerve.drive(
          new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed / 1.5),
          -rotationVal * Constants.Swerve.maxAngularVelocity,
          !robotCentricSup.getAsBoolean(),
          true);
      }
  }
    }

    public static void calledDuringAutotest() {
      //System.out.println("Should move");
      s_Swerve.drive(
        new Translation2d(seekingPieces.transDouble, seekingPieces.strafeDouble).times(Constants.Swerve.maxSpeed / 1.5),
        -seekingPieces.rotationDouble / 2 * Constants.Swerve.maxAngularVelocity,
        !robotCentricSup.getAsBoolean(),
        true);
    }

    public static void calledDuringAutotest2() {
      //System.out.println("Should move2");
      s_Swerve.drive(
        new Translation2d(speakerFinder.transDouble, speakerFinder.strafeDouble).times(Constants.Swerve.maxSpeed / 1.5),
        -speakerFinder.rotationDouble / 2 * Constants.Swerve.maxAngularVelocity,
        !robotCentricSup.getAsBoolean(),
        true);
    }

    public static void zeroAutoGyro() {
      s_Swerve.zeroGyro();
    }

    public static void saveGyroData() {
      seekingPieces.savedGyroYaw = Swerve.yaw;
      System.out.println("Saved gyro angle " + seekingPieces.savedGyroYaw);
    }

    public static void resetGyro() {
      Swerve.yaw = seekingPieces.savedGyroYaw;
      //System.out.println("GYRO RESET!");
      System.out.println(Swerve.yaw);
    }
}
