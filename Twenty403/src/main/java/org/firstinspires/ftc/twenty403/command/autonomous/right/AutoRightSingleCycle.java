package org.firstinspires.ftc.twenty403.command.autonomous.right;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstants;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRightSingleCycle extends SequentialCommandGroup {
    public AutoRightSingleCycle(Robot r) {
        super(
                new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.Right.W_JUNCTION_TO_STACK)
                        .alongWith(new LiftCollectCommand(r.liftSubsystem)),
                new ClawCloseCommand(r.clawSubsystem),
                new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.Right.STACK_TO_W_JUNCTION)
                        .alongWith(new LiftHighJunctionCommand(r.liftSubsystem)),
                new ClawOpenCommand(r.clawSubsystem));
    }
}