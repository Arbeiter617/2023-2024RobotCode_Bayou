// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.simulation.AnalogOutputSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.AnalogInput;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class colorSensor extends SubsystemBase {
  public  final static I2C.Port i2cPort = I2C.Port.kOnboard;
  public final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  public double IR = m_colorSensor.getIR();
  /** Creates a new UpUp. */
  public colorSensor() {
    
      
  };

  

  public void changeSensor (){

  }
 
  
  @Override
  public void periodic() {
    Color detectedColor = m_colorSensor.getColor();
    //SmartDashboard.putNumber("Red", detectedColor.red);

    int proximity = m_colorSensor.getProximity();
    //SmartDashboard.putNumber("Proximity", proximity);

  }


}
