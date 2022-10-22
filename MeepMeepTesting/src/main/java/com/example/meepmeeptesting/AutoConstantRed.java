package com.example.meepmeeptesting;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public class AutoConstantRed {
    public static class Away {
        public static Pose2d START = new Pose2d(36, -66, toRadians(90));
        public static Pose2d STACK = new Pose2d(60, -12, toRadians(0));

        public static Pose2d PARK_LEFT = new Pose2d(12, -36, toRadians(-90));
        public static Pose2d PARK_MIDDLE = new Pose2d(36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(60, -36, toRadians(180));

        public static Pose2d E_JUNCTION = new Pose2d(27, -5, toRadians(120));
        public static Pose2d S_JUNCTION = new Pose2d(4, -28, toRadians(135));

        public static Pose2d BETWEEN = new Pose2d(33, -10, toRadians(10));

        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static double MAX_VEL = 50;
        public static double MAX_ACCEL = 40;
        public static double MAX_ANG_VEL = Math.toRadians(180);
        public static double MAX_ANG_ACCEL = Math.toRadians(120);
        public static double TRACK_WIDTH = 9.5;

        public static MinVelocityConstraint MIN_VEL = new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(MAX_ANG_VEL),
                new MecanumVelocityConstraint(MAX_VEL, TRACK_WIDTH)
        ));
        public static ProfileAccelerationConstraint PROF_ACCEL = new ProfileAccelerationConstraint(MAX_ACCEL);
        public static Function<Pose2d, TrajectoryBuilder> function = pose -> new TrajectoryBuilder(pose, MIN_VEL, PROF_ACCEL);
        public static Supplier<Trajectory>

                START_TO_W_JUNCTION =
                () -> function.apply(START).splineTo(E_JUNCTION.vec(), E_JUNCTION.getHeading()).build(),
        //START_TO_SOUTH_JUNCTION =
        // ()-> function.apply(START).lineToLinearHeading(SOUTH_JUNCTION).build(),
        W_JUNCTION_TO_STACK =
                () -> function.apply(E_JUNCTION).lineToLinearHeading(STACK).build(),
        //SOUTH_JUNCTION_TO_STACK = b->b.apply(JUNCTION).lineToLinearHeading(STACK).build(),
        STACK_TO_W_JUNCTION =
                () -> function.apply(STACK).lineToLinearHeading(E_JUNCTION).build(),
        //STACK_TO_SOUTH_JUNCTION =
        // ()->function.apply(STACK).lineToLinearHeading(JUNCTION).build(),
        W_JUNCTION_TO_PARK_LEFT =
                () -> function.apply(E_JUNCTION).lineToLinearHeading(PARK_LEFT).build(),
                W_JUNCTION_TO_PARK_MIDDLE =
                        () -> function.apply(E_JUNCTION).lineToLinearHeading(PARK_MIDDLE).build(),
                W_JUNCTION_TO_PARK_RIGHT =
                        () -> function.apply(E_JUNCTION).lineToLinearHeading(PARK_RIGHT).build();
        //SOUTH_JUNCTION_TO_PARK_LEFT =
        // ()->function.apply(JUNCTION).lineToLinearHeading(PARK_LEFT).build()
        //SOUTH_JUNCTION_TO_PARK_MIDDLE =
        // ()->function.apply(JUNCTION).lineToLinearHeading(PARK_MIDDLE).build()
        //SOUTH_JUNCTION_TO_PARK_RIGHT =
        // ()->function.apply(JUNCTION).lineToLinearHeading(PARK_RIGHT).build()

    }

    public static class Home {
        public static Pose2d START = new Pose2d(-36, -66, toRadians(90));
        public static Pose2d STACK = new Pose2d(-62, -12, toRadians(180));
        public static Pose2d PARK_LEFT = new Pose2d(-60, -36, toRadians(0));
        public static Pose2d PARK_MIDDLE = new Pose2d(-36, -36, toRadians(-90));
        public static Pose2d PARK_RIGHT = new Pose2d(-12, -36, toRadians(-90));
        public static Pose2d E_JUNCTION = new Pose2d(-28, -4, toRadians(13));
        public static Pose2d S_JUNCTION = new Pose2d(-4, -28, toRadians(45));
        public static Pose2d E_JUNCTION_STACK_BETWEEN = new Pose2d(-45, -12, toRadians(0));

        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}
        public static double MAX_VEL = 50;
        public static double MAX_ACCEL = 40;
        public static double MAX_ANG_VEL = Math.toRadians(180);
        public static double MAX_ANG_ACCEL = Math.toRadians(120);
        public static double TRACK_WIDTH = 9.5;

        public static MinVelocityConstraint MIN_VEL = new MinVelocityConstraint(Arrays.asList(
                new AngularVelocityConstraint(MAX_ANG_VEL),
                new MecanumVelocityConstraint(MAX_VEL, TRACK_WIDTH)
        ));
        public static ProfileAccelerationConstraint PROF_ACCEL = new ProfileAccelerationConstraint(MAX_ACCEL);
        public static Function<Pose2d, TrajectoryBuilder> function = pose -> new TrajectoryBuilder(pose, MIN_VEL, PROF_ACCEL);
        public static Supplier<Trajectory>


                START_TO_NINEOCLOCK_JUNCTION =
                () -> function.apply(START)
                        .splineTo(E_JUNCTION.vec(), E_JUNCTION.getHeading())
                        .build(),
        //START_TO_SIXOCLOCK_JUNCTION=
        //   () -> function.apply(START).lineToLinearHeading().build()
        NINEOCLOCK_TO_STACK =
                () -> function.apply(E_JUNCTION)
                        .lineToLinearHeading(STACK)
//                            .splineTo(E_JUNCTION.vec(), E_JUNCTION.getHeading()
                        .build(),
                STACK_TO_NINEOCLOCK_JUNCTION =
                        () -> function.apply(STACK)
                                .lineToLinearHeading(E_JUNCTION)
                                .build(),
        //STACK_TO_SIXOCLOCK_JUNCTION=
        //() -> function.apply(STACK).lineToLinearHeading().build(),
        NINEOCLOCK_JUNCTION_TO_PARK_LEFT =
                () -> function.apply(E_JUNCTION)
                        .lineToLinearHeading(PARK_LEFT)
                        .build(),
                NINEOCLOCK_JUNCTION_TO_PARK_RIGHT =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(PARK_RIGHT)
                                .build(),
                NINEOCLOCK_JUNCTION_TO_PARK_MIDDLE =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(PARK_MIDDLE)
                                .build(),
                SIXOCLOCK_JUNCTION_TO_PARK_LEFT =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(PARK_LEFT)
                                .build(),
                SIXOCLOCK_JUNCTION_TO_PARK_RIGHT =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(PARK_RIGHT)
                                .build(),
                SIXOCLOCK_JUNCTION_TO_PARK_MIDDLE =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(PARK_MIDDLE)
                                .build(),
                E_JUNCTION_TO_BETWEEN =
                        () -> function.apply(E_JUNCTION)
                                .lineToLinearHeading(E_JUNCTION)
                                .build();

    }

}

