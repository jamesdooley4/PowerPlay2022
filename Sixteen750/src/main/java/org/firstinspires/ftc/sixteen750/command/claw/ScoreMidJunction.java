package org.firstinspires.ftc.sixteen750.command.claw;

import org.firstinspires.ftc.sixteen750.command.claw.Servos.ElbowMidJunction;
import org.firstinspires.ftc.sixteen750.command.claw.Servos.FlipperMidJunction;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.SequentialCommandGroup;

public class ScoreMidJunction extends SequentialCommandGroup {
    public ScoreMidJunction(ClawSubsystem s) {
        super(new FlipperMidJunction(s).alongWith(new ElbowMidJunction(s), new ClawOpenCommand(s)));
    }
}