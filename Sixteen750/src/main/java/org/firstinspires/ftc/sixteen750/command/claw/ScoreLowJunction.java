package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.command.claw.Servos.ElbowLowJunction;
import org.firstinspires.ftc.sixteen750.command.claw.Servos.FlipperLowJunction;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ScoreLowJunction extends SequentialCommandGroup {
    public ScoreLowJunction(ClawSubsystem s) {
        super(new FlipperLowJunction(s).alongWith(new ElbowLowJunction(s), new ClawOpenCommand(s)));
    }
}