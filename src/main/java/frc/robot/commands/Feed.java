package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class Feed extends Command{
    private Intake s_Intake;
    
    public Feed(Intake s_Intake){
        this.s_Intake = s_Intake;
        addRequirements(s_Intake);
    }

    public void initializize(){

    }

    @Override
    public void execute(){
        s_Intake.feedShooter();
    }

    @Override
    public void end(boolean interrupted) {
        s_Intake.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
