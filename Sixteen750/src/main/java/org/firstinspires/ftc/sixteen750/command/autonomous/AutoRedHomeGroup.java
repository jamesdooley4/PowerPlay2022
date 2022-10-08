package org.firstinspires.ftc.sixteen750.command.autonomous;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.claw.ClawOpenCommand;
import org.firstinspires.ftc.sixteen750.command.cone.ConeReadyToScoreHigh;
import org.firstinspires.ftc.sixteen750.subsystem.ClawSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.ConeSubsystem;
import org.firstinspires.ftc.sixteen750.subsystem.LiftSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.subsystem.drivebase.DrivebaseSubsystem;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRedHomeGroup extends SequentialCommandGroup {
    public AutoRedHomeGroup(DrivebaseSubsystem drive, ClawSubsystem claw, ConeSubsystem cone, LiftSubsystem lift) {
        super(
                new TrajectorySequenceCommand(drive, Robot.Trajectories.HIGH_JUNCTION_HOME)
                        .alongwith(new ConeReadyToScoreHigh(cone)),
                new ClawOpenCommand(claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new AutoRedHomeConeStackCommand(drive, cone, lift, claw),
                new TrajectorySequenceCommand(Robot.Trajectories.PARK_HOME));
    }
}
