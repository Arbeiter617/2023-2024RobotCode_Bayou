package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autoCommands.autoCommandsToRun;
import frc.robot.autoCommands.intakeDown;
import frc.robot.autoCommands.intakeUp;
import frc.robot.autoCommands.seekingPieces;
import frc.robot.autoCommands.seekingPiecesEnd;
import frc.robot.autoCommands.shooterActuatorA;
import frc.robot.autoCommands.speakerFinder;
import frc.robot.autoCommands.speakerFinderEnd;
import frc.robot.autoCommands.zeroGyro;
import frc.robot.commands.*;
import frc.robot.commands.ampBarCommands.ampAssistControl;
import frc.robot.commands.automatedCommands.autoClimb;
import frc.robot.commands.automatedCommands.automatedIntake;
import frc.robot.commands.automatedCommands.automatedShooting;
import frc.robot.commands.climberCommands.climberLeft;
import frc.robot.commands.climberCommands.climberRight;
import frc.robot.commands.intakeCommands.intakeChain;
import frc.robot.commands.intakeCommands.intakeIntake;
import frc.robot.commands.limelightCommands.checkingArray;
import frc.robot.commands.limelightCommands.limelightReadingTool;
import frc.robot.commands.limelightCommands.pieceDetectionStuff;
import frc.robot.commands.limelightCommands.readAprilTags;
import frc.robot.commands.limelightCommands.toggleLimelight;
import frc.robot.commands.sensorCommands.colorSensorRun;
import frc.robot.commands.shooterCommands.shooterActuator;
import frc.robot.commands.shooterCommands.shooterShoot;
import frc.robot.controls.controls;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Boolean drivepls = false;
  public static Boolean rotateToPiece = false;

  /* Controllers */
  public final static Joystick driver = new Joystick(0);
  public final static Joystick copilot_Xbox = new Joystick(1);
  public final static Joystick copilot_flightStick = new Joystick(2);
  public final static Joystick copilot_arduino = new Joystick(3);
  
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
  public static shuffleBoardCommands shuffleBoardCommands = new shuffleBoardCommands();
  public static colorSensorRun colorSensorRun = new colorSensorRun();
  public static toggleLimelight toggleLimelight = new toggleLimelight();
  public static ampAssistControl ampAssistControl = new ampAssistControl();
  public static ampControl ampControl = new ampControl();
  public static seekingPieces seekingPieces = new seekingPieces();
  public static automatedIntake automatedIntake = new automatedIntake();
  public static automatedShooting automatedShooting = new automatedShooting();
  //public static TeleopSwerve teleopSwerve = new TeleopSwerve(null, null, null, null, null);

  //april tag commands//
  public static frc.robot.commands.limelightCommands.limelightAutoCommands.amp amp = new frc.robot.commands.limelightCommands.limelightAutoCommands.amp();
  public static frc.robot.commands.limelightCommands.limelightAutoCommands.speaker speaker = new frc.robot.commands.limelightCommands.limelightAutoCommands.speaker();
  public static frc.robot.commands.limelightCommands.limelightAutoCommands.noneFound noneFound = new frc.robot.commands.limelightCommands.limelightAutoCommands.noneFound();
  public static limelightReadingTool limelightReadingTool = new limelightReadingTool();
  public static readAprilTags readAprilTags = new readAprilTags();
  public static checkingArray checkingArray = new checkingArray();
  public static pieceDetectionStuff pieceDetectionStuff = new pieceDetectionStuff();
  public static autoClimb autoClimb = new autoClimb();

  //auto commands//
  public static autoCommandsToRun autoCommandsToRun = new autoCommandsToRun();

  //call subsystems//
  private final colorSensor s_ColorSensor = new colorSensor();
  private final shooterPigeon s_ShooterPigeon = new shooterPigeon();

  public static PWM leds = new PWM(8);

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
 
    NamedCommands.registerCommand("seekPiece", new seekingPieces());
    NamedCommands.registerCommand("seekPieceEnd", new seekingPiecesEnd());
    NamedCommands.registerCommand("speakerFinder", new speakerFinder());
    NamedCommands.registerCommand("speakerFinderEnd", new speakerFinderEnd());
    NamedCommands.registerCommand("zeroGyro", new zeroGyro());
    NamedCommands.registerCommand("shooterActuator", new shooterActuatorA());
    NamedCommands.registerCommand("intakeDown", new intakeDown());
    NamedCommands.registerCommand("intakeUp", new intakeUp());
    
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
    return new PathPlannerAuto("auto");
    //return null;
  }

}
