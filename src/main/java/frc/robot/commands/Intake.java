package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class Intake extends Command {
    private frc.robot.subsystems.Intake s_Intake;
    private DigitalInput beam = new DigitalInput(Constants.IntakeConstants.beamID);
    

    public Intake(frc.robot.subsystems.Intake s_Intake) {
        this.s_Intake = s_Intake;
        addRequirements(s_Intake);
    }

    @Override
    public void execute() {
        s_Intake.pickup();
        while(!beam.get()) {
            if(beam.get()) {
                s_Intake.stop();
            }
        }
    }
}
