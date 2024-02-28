package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private VictorSPX motor;

    public Intake(){
        motor = new VictorSPX(Constants.IntakeConstants.intakeID);
        motor.setInverted(Constants.IntakeConstants.inverted);

        stop();
    }

    public void pickup() {
        motor.set(ControlMode.Velocity, Constants.IntakeConstants.speed);
    }

    public void stop() {
        motor.set(ControlMode.Velocity, 0);
    }

    public void amp() {
        motor.set(ControlMode.Velocity, -Constants.IntakeConstants.speed / 3);
    }

    public void feedShooter() {
        motor.set(ControlMode.Velocity, Constants.IntakeConstants.speed / 3);
    }

    public void retry() throws InterruptedException {
        motor.set(ControlMode.Velocity, -Constants.IntakeConstants.speed / 3);
        wait(100);
        feedShooter();
    }
}
