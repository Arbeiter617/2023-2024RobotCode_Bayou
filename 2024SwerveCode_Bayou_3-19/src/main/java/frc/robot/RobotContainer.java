package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.commands.climberCommands.climberLeft;
import frc.robot.commands.climberCommands.climberRight;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.canRunFileManager;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Boolean drivepls = false;

  /* Controllers */
  public final static Joystick driver = new Joystick(0);
  public final static Joystick copilot = new Joystick(1); 
  //call commands//
  public static climberLeft climberLeft = new climberLeft();
  public static climberRight climberRight = new climberRight();
  public static intakeChain intakeChain = new intakeChain();
  public static intakeIntake intakeIntake = new intakeIntake();
  public static shooterActuator shooterActuator = new shooterActuator();
  public static shooterShoot shooterShoot = new shooterShoot();
  
  public static commandRunner commandRunner = new commandRunner();
  public static controls controls = new controls();
  public static canRunFileManager canRunFileManager = new canRunFileManager();

  //april tag commands//
  public static frc.robot.commands.limelightCommands.limelightAutoCommands.amp amp = new frc.robot.commands.limelightCommands.limelightAutoCommands.amp();
  public static frc.robot.commands.limelightCommands.limelightAutoCommands.speaker speaker = new frc.robot.commands.limelightCommands.limelightAutoCommands.speaker();
  public static frc.robot.commands.limelightCommands.limelightAutoCommands.noneFound noneFound = new frc.robot.commands.limelightCommands.limelightAutoCommands.noneFound();

  //call subsystems//
  private final colorSensor s_ColorSensor = new colorSensor();
  private final shooterPigeon s_ShooterPigeon = new shooterPigeon();

  /* Drive Controls */
  static int translationAxis;
  static int strafeAxis;
  static int rotationAxis;

  /* Driver Buttons */
  private final JoystickButton zeroGyro =
      new JoystickButton(driver, XboxController.Button.kY.value);
  private final JoystickButton robotCentric =
      new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

  /* Subsystems */
  private final Swerve s_Swerve = new Swerve();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //auto commands//

    s_Swerve.setDefaultCommand(
        new TeleopSwerve(
            s_Swerve,
            () -> -driver.getRawAxis(translationAxis),
            () -> -driver.getRawAxis(strafeAxis),
            () -> -driver.getRawAxis(rotationAxis),
            () -> robotCentric.getAsBoolean()));

    // Configure the button bindings
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */
    zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

}
