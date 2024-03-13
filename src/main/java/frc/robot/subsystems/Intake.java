package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private VictorSPX motor;
    private DigitalInput beam;

    public Intake() {
        motor = new VictorSPX(Constants.IntakeConstants.intakeID);
        motor.setInverted(Constants.IntakeConstants.inverted);
        beam = new DigitalInput(Constants.IntakeConstants.beamID);

        stop();
    }

    public void pickup() {
        motor.set(ControlMode.PercentOutput, Constants.IntakeConstants.speed);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput,0);
    }

    public void amp() {
       motor.set(ControlMode.PercentOutput, -Constants.IntakeConstants.speed);
    }

    public void feedShooter() {
        motor.set(ControlMode.PercentOutput, Constants.IntakeConstants.speed * 1.2);
    }

    public void retry() throws InterruptedException {
        motor.set(ControlMode.PercentOutput, -Constants.IntakeConstants.speed / 3);
        feedShooter();
    }

    public void beamBreak() {
        if(!beam.get()){
            motor.set(ControlMode.PercentOutput, 0);
        }
    }
}
