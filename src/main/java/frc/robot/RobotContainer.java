// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  /* Controllers */
  private final Joystick driver = new Joystick(0);
  private final Joystick mech = new Joystick(1);
  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  /* Driver Buttons */
  //private final JoystickButton zeroGyro =
      //new JoystickButton(driver, XboxController.Button.kY.value);
  private final JoystickButton robotCentric =
      new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
  Trigger intakeButton = new JoystickButton(driver, XboxController.Button.kA.value);

  /* Subsystems */
  private final Swerve s_Swerve = new Swerve();
  private final Intake s_Intake = new Intake();
  private final Arm s_Arm = new Arm();
  private final Shooter s_Shooter = new Shooter();
  private final Climb s_Climb = new Climb();

  private final IntakeCommand intakeCommand = new IntakeCommand(s_Intake);
  private final FireShooter fire = new FireShooter(s_Shooter, s_Intake);
  private final LowerClimb lowerClimb = new LowerClimb(s_Climb);
  private final RaiseClimb raiseClimb = new RaiseClimb(s_Climb);
  private final ArmDown armDown = new ArmDown(s_Arm);
  private final ArmUp armUp = new ArmUp(s_Arm);
  private final Amp amp = new Amp(s_Intake);
  private final Feed feed = new Feed(s_Intake);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
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
   * 
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */
    new JoystickButton(mech, XboxController.Button.kA.value).onTrue(intakeCommand);
    new JoystickButton(mech, XboxController.Button.kRightBumper.value).whileTrue(fire);
    new JoystickButton(mech, XboxController.Button.kX.value).whileTrue(amp);
    new POVButton(driver, 180).onTrue(lowerClimb);
    new POVButton(driver, 0).whileTrue(raiseClimb);
    new JoystickButton(mech, XboxController.Button.kY.value).onTrue(new MoveArm(s_Arm, 0.675));
    new JoystickButton(mech, XboxController.Button.kB.value).onTrue(new MoveArm(s_Arm, 0.620));
    new POVButton(mech, 180).onTrue(new MoveArm(s_Arm, 0.782));
    new POVButton(mech, 90).onTrue(new MoveArm(s_Arm, 0.660));
    new POVButton(mech, 270).onTrue(new MoveArm(s_Arm, 0.645));
    new POVButton(mech, 0).onTrue(new MoveArm(s_Arm, 0.536));
    new JoystickButton(driver, XboxController.Button.kY.value).whileTrue(armUp);
    new JoystickButton(driver, XboxController.Button.kA.value).whileTrue(armDown);
    new JoystickButton(mech, XboxController.Button.kLeftBumper.value).whileTrue(feed);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new exampleAuto(s_Swerve);
  }
}
