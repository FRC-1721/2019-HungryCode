/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {

  public static void flyByWire(TalonSRX starboard, TalonSRX port, TalonSRX strafe, Joystick DriverJoystick)
  {
    double thro = DriverJoystick.getRawAxis(1); // Populate thro with axis one
    double yaw = Constants.turnDampener * DriverJoystick.getRawAxis(2); // Populate with axis two
    double roll = Constants.strafeDampener * DriverJoystick.getRawAxis(3);  // Populate with axis three

    starboard.set(ControlMode.PercentOutput, (-1 * thro) - (yaw * RobotMap.invertSteering));  // From the inverse of thro, subtract yaw
    port.set(ControlMode.PercentOutput, thro - (yaw * RobotMap.invertSteering));  // subtract yaw from thro
    strafe.set(ControlMode.PercentOutput, roll);  // Set the strafe motor to the roll percentage

    SmartDashboard.putNumber("Strafe", roll);
    SmartDashboard.putNumber("Thro", thro);
    SmartDashboard.putNumber("Yaw", yaw);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
