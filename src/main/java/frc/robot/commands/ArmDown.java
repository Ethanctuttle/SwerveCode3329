package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class ArmDown extends Command{
    private Arm s_Arm;

    public ArmDown(Arm s_Arm){
        this.s_Arm = s_Arm;
        addRequirements(s_Arm);
    }

    @Override
    public void initialize(){
        s_Arm.lower();
    }

    @Override
    public void execute(){
        s_Arm.lower();
    }

    @Override
    public void end(boolean interrupted){
        s_Arm.stop();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
