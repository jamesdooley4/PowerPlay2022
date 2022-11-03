package org.firstinspires.ftc.sixteen750.command.claw.Servos;

import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;

import com.technototes.library.command.Command;

public class ElbowHighJunction implements Command {
    private ClawSubsystem subsystem;

    public ElbowHighJunction(ClawSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.elbowHighJunction();
    }
}