package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class Amp extends Command{
    private Intake s_Intake;
    
    public Amp(Intake s_Intake){
        this.s_Intake = s_Intake;
        addRequirements(s_Intake);
    }

    public void initializize(){

    }

    @Override
    public void execute(){
        s_Intake.amp();
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
