package org.firstinspires.ftc.swerveteen750.command.lift;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.LiftSubsystem;

public class LiftGroundJunctionCommand implements Command {
    public LiftSubsystem liftSubsystem;

    public LiftGroundJunctionCommand(LiftSubsystem ls) {
        liftSubsystem = ls;
    }

    @Override
    public void execute() {
        liftSubsystem.gotoGroundJunction();
    }
}
