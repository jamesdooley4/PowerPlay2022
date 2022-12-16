package org.firstinspires.ftc.swerveteen750.command.drive;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.swerveteen750.subsystem.drive.MecanumDriveSubsystem;

public class ApplyTurboModeCommand implements Command {
    MecanumDriveSubsystem driveSubsystem;

    public ApplyTurboModeCommand(MecanumDriveSubsystem driveSubsystem){
        this.driveSubsystem = driveSubsystem;
    }
    @Override
    public void execute() {
        driveSubsystem.applyTurboMode();
    }
}
