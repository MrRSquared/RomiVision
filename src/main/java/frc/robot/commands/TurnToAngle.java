// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.RomiDrivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnToAngle extends PIDCommand {
  /** Creates a new TurnToAngle. */
  public TurnToAngle(double targetAngleDegrees, RomiDrivetrain drive) {
    super(
      
        // The controller that the command will use
        new PIDController(DriveConstants.kP, DriveConstants.kI, DriveConstants.kD),
       // Close loop on heading
       drive::getGyroAngleZ,
       // Set reference to target
       targetAngleDegrees,
       // Pipe output to turn robot
       output -> drive.tankDriveVolts(-output, output),
       // Require the drive
       drive);
        //Set the controller to be continuous since it is a gyro.
        getController().enableContinuousInput(-180, 180);
        // setpoint before it is considered as having reached the reference
       getController().setTolerance(DriveConstants.kTurnTolleranceDeg, DriveConstants.kTurnTolleranceDegPerS);
      
  } 

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
