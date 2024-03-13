package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class FireShooter extends Command{
    private Shooter s_Shooter;
    private Intake s_Intake;

    private Timer timer = new Timer();

    public FireShooter(Shooter s_Shooter, Intake s_Intake){
        this.s_Shooter = s_Shooter;
        this.s_Intake = s_Intake;
        addRequirements(s_Shooter);
    }

    public void initializize(){
        timer = new Timer();
    }

    @Override
    public void execute(){
        s_Shooter.fire();

        timer.start();

        //if (timer.hasElapsed(2)) {
           // s_Intake.feedShooter();
            //timer.reset();
        //}

    }

    @Override
    public void end(boolean interrupted) {
        s_Intake.stop();
        s_Shooter.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
