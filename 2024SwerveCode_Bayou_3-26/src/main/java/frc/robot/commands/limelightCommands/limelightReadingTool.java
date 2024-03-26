package frc.robot.commands.limelightCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class limelightReadingTool extends Command {
    public static double prev_distance = 0;
    public static double idRead;
    public static double xValue;
    public static double yValue;
    public static double areaValue;
    public static NetworkTable table;

    //for piece-detection limelight//
    public static double xValuePD;
    public static double yValuePD;
    public static double areaValuePD;
    public static NetworkTable tablePD;
     public limelightReadingTool() {
     }
   
     @Override
     public void initialize() {}
   
     @Override
     public void execute() {
        table = NetworkTableInstance.getDefault().getTable("limelight-aprilag");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry id = table.getEntry("tid");
        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        idRead = id.getDouble(0.0);
        xValue = x;
        yValue = y;
        areaValue = area;

        //second limelight//
        tablePD = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry txPD = tablePD.getEntry("tx");
        NetworkTableEntry tyPD = tablePD.getEntry("ty");
        NetworkTableEntry taPD = tablePD.getEntry("ta");

        //read values periodically
        double xPD = txPD.getDouble(0.0);
        double yPD = tyPD.getDouble(0.0);
        double areaPD = taPD.getDouble(0.0);
        xValuePD = xPD;
        yValuePD = yPD;
        areaValuePD = areaPD;


        
    }
}