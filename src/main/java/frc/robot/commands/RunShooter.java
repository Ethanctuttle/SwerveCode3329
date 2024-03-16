package frc.robot.commands;

import edu.wpi.first.units.Time;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class RunShooter extends Command{
    private Shooter s_Shooter;
    private Intake s_Intake;
    private Timer timer = new Timer();

    public RunShooter(Shooter s_Shooter, Intake s_Intake){
        this.s_Shooter = s_Shooter;
        this.s_Intake = s_Intake;
        timer.start();
        addRequirements(s_Shooter, s_Intake);
    }

    public void initializize(){
        
    }

    @Override
    public void execute(){
        s_Shooter.fire();
        Timer.delay(1.5);
        s_Intake.feedShooter();
        Timer.delay(1);
        end(true);
        cancel();
    }

    @Override
    public void end(boolean interrupted){
        s_Shooter.stop();
        s_Intake.stop();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
