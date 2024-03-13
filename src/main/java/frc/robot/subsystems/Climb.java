package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase{
    private VictorSPX motor;
    private DigitalInput limit;

    public Climb(){
        motor = new VictorSPX(14);
        limit = new DigitalInput(3);
    }

    public void up(){
        motor.set(ControlMode.PercentOutput, -1);
    }

    public void down(){
        motor.set(ControlMode.PercentOutput, 1);
    }

    public void stop(){
        motor.set(ControlMode.PercentOutput, 0);
    }

    public void limit(){
        if(limit.get()){
            stop();
        }
    }
}
