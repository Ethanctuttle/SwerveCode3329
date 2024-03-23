// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Auto selector
  private final SendableChooser<Command> autonChooser = new SendableChooser<Command>();

  /* Controllers */
  private final CommandXboxController driver = new CommandXboxController(0);
  private final CommandXboxController mech = new CommandXboxController(1);

  /* Subsystems */
  private final Swerve s_Swerve = new Swerve();
  private final Intake s_Intake = new Intake();
  private final Arm s_Arm = new Arm();
  private final Shooter s_Shooter = new Shooter();
  private final Climb s_Climb = new Climb();
  private final LEDs s_LEDs = new LEDs();

  /* Triggers */
  private final Trigger robotCentric = driver.leftBumper();
  private final Trigger intakeSensor = new Trigger(() -> s_Intake.hasNote());
  private final Trigger climbTopSensor = new Trigger(() -> s_Climb.atHigherLimit());
  private final Trigger climbBottomSensor = new Trigger(() -> s_Climb.atLowerLimit());

  /* Commands */
  private final ShootCommand shootCommand = new ShootCommand(s_Shooter, s_Intake, intakeSensor);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    s_Swerve.setDefaultCommand(
        new TeleopSwerve(
            s_Swerve,
            () -> -driver.getLeftY(),
            () -> -driver.getLeftX(),
            () -> -driver.getRightX(),
            () -> robotCentric.getAsBoolean()));

    // Add different auto programs
    autonChooser.setDefaultOption("Drive off line", new MoveBackToNoteAuto(s_Swerve));
    autonChooser.addOption("shoot and move",
        new ShootAuto(s_Arm, s_Intake, s_Shooter, intakeSensor, 41.52).andThen(new MoveBackToNoteAuto(s_Swerve)));
    autonChooser.addOption("shoot, move, intake, shoot",
        new ShootAuto(s_Arm, s_Intake, s_Shooter, intakeSensor, 41.52)
            .andThen(s_Intake.intakeCommand().until(intakeSensor).withTimeout(5)
                .alongWith(new MoveBackToNoteAuto(s_Swerve)))
            .andThen(() -> s_Swerve.stop(), s_Swerve)
            .andThen(s_Arm.moveArmCommand(47))
            .andThen(new ShootCommand(s_Shooter, s_Intake, intakeSensor).withTimeout(2))
            .andThen(s_Arm.moveArmCommand(Constants.ArmConstants.kArmDown)));
    autonChooser.addOption("Angled Auto One", new AngledAutoOne(s_Swerve, s_Arm, s_Shooter, s_Intake, intakeSensor));

    // Configure the controller button bindings
    configureButtonBindings();

    // auto triggers
    intakeSensor.onTrue(s_LEDs.turnOnCommand()).onFalse(s_LEDs.turnOffCommand());
    SmartDashboard.putData("Auton Chooser", autonChooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * 
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */
    // dpad
    driver.povDown().and(climbBottomSensor.negate()).whileTrue(s_Climb.hooksDown());
    driver.povUp().and(climbTopSensor.negate()).whileTrue(s_Climb.hooksUp());

    /* Operator buttons */
    // bumpers
    mech.rightBumper()
        .onTrue(shootCommand.withTimeout(1.2));
    mech.leftBumper().onTrue(s_Arm.moveArmCommand(56));

    // axyb
    mech.a()
        .and(intakeSensor.negate())
        .whileTrue(s_Intake.intakeCommand());
    mech.x().whileTrue(s_Intake.runAmp());
    mech.y().onTrue(s_Arm.moveArmCommand(36));
    mech.b().onTrue(s_Arm.moveArmCommand(56));

    // dpad
    mech.povUp().onTrue(s_Arm.moveArmCommand(89));
    mech.povLeft().onTrue(s_Arm.moveArmCommand(47));
    mech.povRight().onTrue(s_Arm.moveArmCommand(42));
    mech.povDown().onTrue(s_Arm.moveArmCommand(-3.8));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonChooser.getSelected();
  }
}
