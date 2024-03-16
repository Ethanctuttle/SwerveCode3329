package frc.robot.autos;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FeedAuto;
import frc.robot.commands.MoveArm;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class auto1 extends SequentialCommandGroup {

  public auto1(Arm s_Arm, Intake s_Intake, Shooter s_Shooter) {
    //Timer timer = new Timer();
    //timer.start();
    SequentialCommandGroup first = new SequentialCommandGroup(new MoveArm(s_Arm, 0.642), new WaitCommand(5), new RunShooter(s_Shooter, s_Intake), new WaitCommand(3), new MoveArm(s_Arm, 0.78));
    //SequentialCommandGroup second = new SequentialCommandGroup(new MoveArm(s_Arm, 0.78));
    first.schedule();
    /*if(timer.hasElapsed(1.5)){
      second.schedule();
    }*/
  }
}