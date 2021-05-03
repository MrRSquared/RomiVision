// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.time.Instant;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();
  private final Intake m_intake = new Intake();

  // Assumes a gamepad plugged into channnel 0
  private final Joystick m_controller = new Joystick(0);
  private TurnToAngle m_turnToAngle;
  private SequentialCommandGroup m_auto;

  // Create SmartDashboard chooser for autonomous routines
  // private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /**
     * This shows how to create an instant command group that completes an
     * intialization Then turns to 90 Degrees, Finally, it stops. All of this logic
     * could have been rolled into the command itself, but I wanted to outline how
     * to do it independently.
     */
    /**
     * m_chooser.setDefaultOption("Turn To 90", new InstantCommand( () ->
     * m_romiDrivetrain.resetEncoders()) .andThen(new TurnToAngle(90,
     * m_romiDrivetrain)) .andThen(new InstantCommand(() ->
     * m_romiDrivetrain.arcadeDrive(0, 0))) );
     */

    JoystickButton intakeButton = new JoystickButton(m_controller, 2);
    // Binds an ExampleCommand to be scheduled when the trigger of the example joystick is pressed
intakeButton.whenPressed(runIntake());

    m_turnToAngle = new TurnToAngle(90, m_romiDrivetrain);
    m_auto = new SequentialCommandGroup(
      new InstantCommand(() -> m_romiDrivetrain.resetEncoders()),
      m_turnToAngle,
      new InstantCommand(() -> m_romiDrivetrain.arcadeDrive(0, 0))
    );
    JoystickButton exampleButton = new JoystickButton(m_controller, 1); // Creates a new JoystickButton object for button 1 on exampleStick
    JoystickButton exampleButton2 = new JoystickButton(m_controller, 2);
    exampleButton
      .whenPressed(m_auto);
    exampleButton2
      .cancelWhenActive(m_auto);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_chooser.getSelected();
    /**return new InstantCommand(() -> m_romiDrivetrain.resetEncoders())
      .andThen(m_turnToAngle)
      .andThen(new InstantCommand(() -> m_romiDrivetrain.arcadeDrive(0, 0))
    );*/
    return m_auto;

  }
  public Command runIntake(){
    return new InstantCommand(()->m_intake.runIntake(0.5));

  }
}
