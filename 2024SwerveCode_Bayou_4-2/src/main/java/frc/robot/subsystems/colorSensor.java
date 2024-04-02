// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;

public class colorSensor extends SubsystemBase {
  public  final static I2C.Port i2cPort = I2C.Port.kOnboard;
  public final static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  public double IR = m_colorSensor.getIR();
  /** Creates a new UpUp. */
  public colorSensor() {
    
      
  };

  

  public void changeSensor (){

  }


}
