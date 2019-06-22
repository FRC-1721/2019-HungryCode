/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.OI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;

  @Override
  public void robotInit() {
    SmartDashboard.putString("Good ", "Morning! ");
    CameraServer.getInstance().startAutomaticCapture();

    m_oi = new OI();
    // Define Joysticks
    RobotMap.driverStick = new Joystick(RobotMap.driverStickPort);  // Define driver stick

    // Define motors
    RobotMap.starboardMotor = new TalonSRX(RobotMap.starboardAddress);
    RobotMap.portMotor = new TalonSRX(RobotMap.portAddress);
    RobotMap.strafeMotor = new TalonSRX(RobotMap.strafeAddress);

    // Define Pneumatics
    RobotMap.candyLift = new DoubleSolenoid(RobotMap.candyLiftUpPort, RobotMap.candyLiftDownPort);
    RobotMap.hornSolenoid = new DoubleSolenoid(RobotMap.hornPort, RobotMap.hornPort2);
  }

  @Override
  public void robotPeriodic() {
    // Called all the time no matter the mode
    if(Constants.safeMode != true) {
      DriveTrain.flyByWire(RobotMap.starboardMotor, RobotMap.portMotor, RobotMap.strafeMotor, RobotMap.driverStick, Constants.turnDampener, Constants.strafeDampener, Constants.throDampener, true);
    }
    else
    {
      DriveTrain.flyByWire(RobotMap.starboardMotor, RobotMap.portMotor, RobotMap.strafeMotor, RobotMap.driverStick, Constants.debugTurnDampener, Constants.debugStrafeDampener, Constants.debugThroDampener, true);
    }
  }

  @Override
  public void disabledInit() {
    // Called once when disabled
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //Runs once
  }

  @Override
  public void autonomousPeriodic() {
    // This function is called periodically during autonomous.
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //if (m_autonomousCommand != null) {
    //  m_autonomousCommand.cancel();
    //}
    Constants.safeMode = false;
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // This function is called periodically during operator control.
  }

  @Override
  public void testPeriodic() {
    // Called periodically during test mode
    Constants.safeMode = true;
    SmartDashboard.putNumber("StrafeDampener", Constants.debugStrafeDampener);
    SmartDashboard.putNumber("TurnDampener", Constants.debugTurnDampener);
    SmartDashboard.putNumber("ThroDampener", Constants.debugThroDampener);
    SmartDashboard.putNumber("Inverted Steering", RobotMap.invertSteering);
  }
}