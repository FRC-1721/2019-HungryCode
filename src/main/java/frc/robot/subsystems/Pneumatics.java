/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static void toggle(Joystick controller, DoubleSolenoid piston, boolean verbose){
    boolean movement;
    if(piston.get() == DoubleSolenoid.Value.kForward){  // If already forward
      piston.set(DoubleSolenoid.Value.kReverse);  // Reverse
      movement = false;
    }else{
      piston.set(DoubleSolenoid.Value.kForward);  // If not already forward, set to forward
      movement = true;
    }

    if(verbose == true)
    {
      SmartDashboard.putBoolean("Solenoid Movement", movement);
    }
  }
}