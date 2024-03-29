package frc.robot.commands;

// import edu.wpi.first.wpilibj.DigitalInput;//
import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.Constants;//
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    private frc.robot.subsystems.Intake s_Intake;

    public IntakeCommand(Intake s_Intake) {
        this.s_Intake = s_Intake;
        addRequirements(s_Intake);
    }

    public void initializize() {
        
    }

    @Override
    public void execute() {
        s_Intake.pickup();
        s_Intake.beamBreak();
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
