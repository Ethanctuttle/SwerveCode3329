package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Feed;
import frc.robot.commands.FireShooter;
import frc.robot.commands.MoveArm;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class auto1 extends SequentialCommandGroup {
  private Timer timer = new Timer();

  public auto1(Arm s_Arm, Intake s_Intake, Shooter s_Shooter) {
    addCommands(
        new MoveArm(s_Arm, 0.642),
        new FireShooter(s_Shooter, s_Intake));
        timer.start();
        if(timer.hasElapsed(1.5)){
          new Feed(s_Intake);
        }
  }
}