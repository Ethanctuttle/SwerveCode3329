package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class FeedAuto extends Command{
    private Intake s_Intake;

    public FeedAuto(Intake s_Intake){
        this.s_Intake = s_Intake;
        addRequirements(s_Intake);
    }

    public void initializize(){
        s_Intake.feedShooter();
    }

    @Override
    public void execute(){
        s_Intake.feedShooter();
    }

    @Override
    public void end(boolean interrupted){
        
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
