package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arm;

public class MoveArm extends Command{
    private Arm s_Arm;
    private double targetPosition;
    
    public MoveArm(Arm s_Arm, double targetPosition){
        this.s_Arm = s_Arm;
        this.targetPosition = targetPosition;
        addRequirements(s_Arm);
    }

    @Override
    public void initialize(){
        s_Arm.setTargetPosition(targetPosition);
    }

    @Override
    public void execute(){}

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return true;
    }
}