package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climb;

public class RaiseClimb extends Command{
    private Climb s_Climb;
    
    public RaiseClimb(Climb s_Climb){
        this.s_Climb = s_Climb;
        addRequirements(s_Climb);
    }

    public void initializize(){

    }

    @Override
    public void execute(){
        s_Climb.up();
        s_Climb.limit();
    }

    @Override
    public void end(boolean interrupted) {
        s_Climb.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
