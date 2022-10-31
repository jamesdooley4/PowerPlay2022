package org.firstinspires.ftc.twenty403.command.autonomous.red_away;

import org.firstinspires.ftc.twenty403.command.autonomous.AutoConstantsRed;
import org.firstinspires.ftc.twenty403.subsystem.DrivebaseSubsystem;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

public class AutoRedAwayLeft extends SequentialCommandGroup {
    public AutoRedAwayLeft(DrivebaseSubsystem drivebaseSubsystem) {
        super(new TrajectorySequenceCommand(drivebaseSubsystem, AutoConstantsRed.Away.START_TO_LEFT_PARK));
    }
}