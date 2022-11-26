package org.firstinspires.ftc.sixteen750.command.lift2;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem2;

public class Lift2HighJunction implements Command {
    private LiftSubsystem2 liftSubsystem2;

    public Lift2HighJunction(LiftSubsystem2 l2){
        liftSubsystem2 = l2;
    }
    @Override
    public void execute() {
        liftSubsystem2.highPole();
    }
}
