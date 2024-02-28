package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    private frc.robot.subsystems.Intake s_Intake;
    private DigitalInput beam = new DigitalInput(Constants.IntakeConstants.beamID);
    

    public IntakeCommand(Intake s_Intake) {
        this.s_Intake = s_Intake;
        addRequirements(s_Intake);
    }

    public void initializize() {
        s_Intake.pickup();
    }

    @Override
    public void execute() {
        
    }
}
