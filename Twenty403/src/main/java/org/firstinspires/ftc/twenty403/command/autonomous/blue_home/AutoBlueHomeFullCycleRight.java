package org.firstinspires.ftc.twenty403.command.autonomous.blue_home;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.twenty403.command.claw.ClawCloseCommand;
import org.firstinspires.ftc.twenty403.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftCollectCommand;
import org.firstinspires.ftc.twenty403.command.lift.LiftHighJunctionCommand;
import org.firstinspires.ftc.twenty403.subsystem.ClawSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

// autonomous for blue home and red away
// parks in right position
// scores preload & 2 more cones from stack
public class AutoBlueHomeFullCycleRight extends SequentialCommandGroup {
    public AutoBlueHomeFullCycleRight(
            DrivebaseSubsystem drivebaseSubsystem, ClawSubsystem clawSubsystem, LiftSubsystem liftSubsystem) {
        super(
                new ClawCloseCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.START_TO_W_JUNCTION)
                        .alongWith(new SequentialCommandGroup(
                                new WaitCommand(0.2), new LiftHighJunctionCommand(liftSubsystem))),
                new ClawOpenCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.W_JUNCTION_TO_STACK)
                        .alongWith(new LiftCollectCommand(liftSubsystem)),
                new ClawCloseCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.STACK_TO_W_JUNCTION)
                        .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                new ClawOpenCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.W_JUNCTION_TO_STACK)
                        .alongWith(new LiftCollectCommand(liftSubsystem)),
                new ClawCloseCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.STACK_TO_W_JUNCTION)
                        .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                new ClawOpenCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.W_JUNCTION_TO_STACK)
                        .alongWith(new LiftCollectCommand(liftSubsystem)),
                new ClawCloseCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.STACK_TO_W_JUNCTION)
                        .alongWith(new LiftHighJunctionCommand(liftSubsystem)),
                new ClawOpenCommand(clawSubsystem),
                new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsBlue.Home.W_JUNCTION_TO_RIGHT_PARK)
                        .alongWith(new LiftCollectCommand(liftSubsystem)));
    }
}