package org.firstinspires.ftc.sixteen750.command.lift2;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem2;

public class Lift2MoveDown implements Command {
    private LiftSubsystem2 liftSubsystem2;

    public Lift2MoveDown(LiftSubsystem2 l2){
        liftSubsystem2 = l2;
    }
    @Override
    public void execute() {
        liftSubsystem2.moveDown();
    }
}
