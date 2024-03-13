package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase{
    private VictorSPX left, right;
    private DutyCycleEncoder encoder;
    private ProfiledPIDController pid;
    private double target = Constants.ArmConstants.inside;
    public double p, i, d;

    public Arm() {
        left = new VictorSPX(Constants.ArmConstants.leftID);
        right = new VictorSPX(Constants.ArmConstants.rightID);
        left.setInverted(!Constants.ArmConstants.inverted);
        right.setInverted(Constants.ArmConstants.inverted);
        
        p = 8;
        i = 0.1;
        d = 0.25;
        pid = new ProfiledPIDController(p, i, d, new Constraints(1, 0.5));
        pid.enableContinuousInput(0, 1);

        setTargetPosition(target);

        encoder = new DutyCycleEncoder(2);
        encoder.reset();
    }

    public void setSpeed(double power){
        left.set(ControlMode.PercentOutput, power);
        right.set(ControlMode.PercentOutput, power);
    }
    
    public void moveArm(double power){
        setSpeed(power);
    }

    public double getPosition(){
        return encoder.getAbsolutePosition();
    }
    
    public void setTargetPosition(double position){
        target = position;
        pid.setGoal(target);
    }

    public double getTargetPosition(){
        return target;
    }

    @Override
    public void periodic(){
        setSpeed(pid.calculate(getPosition()));
        SmartDashboard.putNumber("Encoder", encoder.getAbsolutePosition());
        SmartDashboard.putNumber("Target", target);
    }

    public void lift(){
        setSpeed(1);
    }

    public void lower(){
        setSpeed(-1);
    }

    public void stop(){
        setSpeed(0);
    }
}
