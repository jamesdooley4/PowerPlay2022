package org.firstinspires.ftc.sixteen750.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.command.VisionCommand;
import org.firstinspires.ftc.sixteen750.command.autonomous.AutoConstantsBlue;
import org.firstinspires.ftc.sixteen750.command.autonomous.StartingPosition;
import org.firstinspires.ftc.sixteen750.command.autonomous.left.LeftParkingSelectionCommandJustPark;
import org.firstinspires.ftc.sixteen750.command.autonomous.right.RightParkingSelectionCommandJustPark;

@Autonomous(name = "LeftJustPark")
public class LeftJustPark extends CommandOpMode {
    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        hardware = new Hardware(hardwareMap, Robot.SubsystemCombo.VISION_DRIVE);
        robot = new Robot(hardware, Robot.SubsystemCombo.VISION_DRIVE, Alliance.BLUE, StartingPosition.AWAY);
        robot.mecanumDriveSubsystem.setPoseEstimate(AutoConstantsBlue.Away.START.toPose());
        CommandScheduler.getInstance()
                .scheduleForState(
                        new SequentialCommandGroup(
                                new LeftParkingSelectionCommandJustPark(
                                        robot.visionSubsystem, robot.mecanumDriveSubsystem),
                                CommandScheduler.getInstance()::terminateOpMode),
                        CommandOpMode.OpModeState.RUN);
        if (Robot.RobotConstant.CAMERA_ENABLED) {
            CommandScheduler.getInstance().scheduleInit(new VisionCommand(robot.visionSubsystem));
        }
    }
}