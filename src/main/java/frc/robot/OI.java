/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class OI {
    Joystick controller = new Joystick(RobotMap.driverStickPort);
    Button candyLiftButton = new JoystickButton(controller, RobotMap.candyLiftButton);
    Button hornButton = new JoystickButton(controller, RobotMap.hornButton);
    public OI(){
        candyLiftButton.whenPressed(new LiftCandy());
        hornButton.whenPressed(new HonkHorn());
    }
}
