package org.firstinspires.ftc.twenty403.subsystem;

import androidx.core.math.MathUtils;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.technototes.library.subsystem.Subsystem;

@Config
public class LiftSubsystem implements Subsystem, Supplier<Double>, Loggable {
    public static double TICKS_INCH = 265;
    // TODO: THESE VALUES ARE ALL WRONG! THEY NEED TO BE SET TO THE RIGHT VALUES!!!!
    public static double LGROUND_JUNCTION = 0.5 * TICKS_INCH;
    public static double RGROUND_JUNCTION = 0.5 * TICKS_INCH;
    public static double LLOW_JUNCTION = 12 * TICKS_INCH;
    public static double RLOW_JUNCTION = 12 * TICKS_INCH;
    public static double LMEDIUM_JUNCTION = 24 * TICKS_INCH;
    public static double RMEDIUM_JUNCTION = 24 * TICKS_INCH;
    public static double LHIGH_JUNCTION = 36 * TICKS_INCH;
    public static double RHIGH_JUNCTION = 36 * TICKS_INCH;
    public static double MAX_HEIGHT = 38 * TICKS_INCH;
    public static double MIN_HEIGHT = 0;
    public static double MAX_DISTANCE_FOR_FULLPOWER = 8 * TICKS_INCH;
    public static double DEAD_ZONE = .25 * TICKS_INCH;
    public static double MAX_MOTOR_SPEED = 0.3;
    public static double MIN_MOTOR_SPEED = -0.1; // Gravity
    public static double LMOVE = 1.00 * TICKS_INCH;
    public static double RMOVE = 1.00 * TICKS_INCH;
    public static PIDCoefficients PID = new PIDCoefficients(0.008, 0, 0.0005);

    private DcMotorEx liftMotor;

    // True if we only have *one* motor connected
    private boolean singleMotor;
    // True if we actually have hardware attached
    private boolean isHardware;

    // For the left side, positive is *down*
    // For the right side, positive is *up*
    public LiftSubsystem(EncodedMotor<DcMotorEx> lm, EncodedMotor<DcMotorEx> rm) {
        leftMotor = lm;
        leftMotor.setInverted(false);
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);

        rightMotor = rm;
        rightMotor.setInverted(true);
        rightPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        singleMotor = false;
        isHardware = true;
    }

    // Before:
    //2613, -2654
    // 1 inch lower
    //2875, -2923
    // 262, 269
    public LiftSubsystem(EncodedMotor<DcMotorEx> oneMotor) {
        leftMotor = oneMotor;
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        singleMotor = false;
        rightMotor = null;
        rightPidController = null;
        isHardware = true;
    }

    public LiftSubsystem() {
        isHardware = false;
        leftMotor = null;
        rightMotor = null;
        leftPidController = null;
        rightPidController = null;
    }

    private void setPosition(double lpos, double rpos) {
        if (!isHardware) {
            return;
        }
        leftPidController.setTargetPosition(Range.clip(lpos, MIN_HEIGHT, MAX_HEIGHT));
        if (singleMotor == false) {
            rightPidController.setTargetPosition(Range.clip(rpos, MIN_HEIGHT, MAX_HEIGHT));
        }
    }

    private void powerMotorForError(double error) {
        // If we're error distance away from position,
        // set motor power to something that can reach position
        double power = error / MAX_DISTANCE_FOR_FULLPOWER;
        power = MathUtils.clamp(power, -1, 1);
        power = Math.cbrt(power);
        liftMotor.setPower(power);
    }

    public LiftSubsystem(DcMotorEx motor) {
        liftMotor = motor;
    }

    public void stop() {
        if (!isHardware) {
            return;
        }
        // By resetting the pidController, it stops the periodic function from making changes
        leftPidController.reset();
        if (singleMotor == false) {
            rightPidController.reset();
        }
    }

    public void halt() {
        if (!isHardware) {
            return;
        }
        // Just set the target position to the current position to get the motor to stop, yes?
        leftPidController.setTargetPosition(leftMotor.get());
        if (!singleMotor) {
            rightPidController.setTargetPosition(rightMotor.get());
        }
    }

    private void setLiftPosition(double lval, double rval) {
        if (!isHardware) {
            return;
        }
        setPosition(lval, rval);
        lpAndActual = String.format("%d (%d)", (int) lval, leftMotor.get().intValue());
        if (singleMotor == false) {
            rpAndActual = String.format("%d (%d)", (int) rval, rightMotor.get().intValue());
        }
    }

    // This is run for every loop (Feature of the TechnoLib Subsystem!)
    // So you can just call "setTop" in a command and it will get there "as soon as it can"
    @Override
    public void periodic() {
        if (!isHardware) {
            return;
        }
        // Negate leftMoter.get() so that the motor moves in the proper direction
        double ltargetSpeed = leftPidController.update(-leftMotor.get());
        double lclippedSpeed = Range.clip(ltargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
        setLeftPower(lclippedSpeed);
        if (!singleMotor) {
            double rtargetSpeed = rightPidController.update(rightMotor.get());
            double rclippedSpeed = Range.clip(rtargetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
            setRighttPower(rclippedSpeed);
            // For logging purposes, I'm also doing this, to ensure that both values are updated
        }
        double rightTarget = 0;
        if (!singleMotor) {
            rightTarget = rightPidController.getTargetPosition();
        }
        setLiftPosition(leftPidController.getTargetPosition(), rightTarget);
    }

    private void setLeftPower(double p) {
        // leftMotor.setSpeed(p);
        lMotorPower = String.format("Power assigned: %f", p);
    }

    private void setRighttPower(double p) {
        // leftMotor.setSpeed(p);
        rMotorPower = String.format("Power assigned: %f", p);
    }

    //    public double delta() {
    //        return leftPidController.getTargetPosition() - leftMotor.getDevice().getCurrentPosition();
    //        return rightPidController.getTargetPosition() - rightMotor.getDevice().getCurrentPosition();
    //    }

    //    public boolean isAtTarget() {
    //        return Math.abs(delta()) < DEAD_ZONE;
    //    }

    @Log(name = "lEncMotor Pos (Actual)")
    public volatile String lpAndActual = "(unknown)";

    @Log(name = "rEncMotor Pos (Actual)")
    public volatile String rpAndActual = "(unknown)";

    @Log(name = "lMotor Power")
    public volatile String lMotorPower = "n/a";

    @Log(name = "rMotor Power")
    public volatile String rMotorPower = "n/a";

    @Override
    public Double get() {
        if (!isHardware) {
            return 0.0;
        }
        // Not sure about this one
        return leftPidController.getTargetPosition();
        //        return rightPidController.getTargetPosition();
    }

    //    private boolean closeEnough(double currentPostion, double targetPostion) {
    //        double error = currentPostion - targetPostion;
    //        if (error <= DEAD_ZONE && error >= -DEAD_ZONE) {
    //            return true;
    //        } else {
    //            return false;
    //        }
    //    }

    public void highPole() {
        double position = liftMotor.getCurrentPosition();
        if (closeEnough(position, HIGH_JUNCTION)) {
            return;
        }
        double error = position - HIGH_JUNCTION;
        powerMotorForError(error);
    }

    public void midPole() {
        double position = liftMotor.getCurrentPosition();
        if (closeEnough(position, MEDIUM_JUNCTION)) {
            return;
        }
        double error = position - MEDIUM_JUNCTION;
        powerMotorForError(error);
    }

    public void lowPole() {
        double position = liftMotor.getCurrentPosition();
        if (closeEnough(position, LOW_JUNCTION)) {
            return;
        }
        double error = position - LOW_JUNCTION;
        powerMotorForError(error);
    }

    public void moveUp() {
        double position = liftMotor.getCurrentPosition();
        if (position >= MAX_HEIGHT) {
            return;
        }
        double error = position - MAX_HEIGHT;
        powerMotorForError(error);
    }

    public void moveDown() {

        double position = liftMotor.getCurrentPosition();
        if (position <= MIN_HEIGHT) {
            return;
        }
        double error = position - MIN_HEIGHT;
        powerMotorForError(error);
    }

    public void intake() {
        // TODO:
    }

    public void carry() {
        // TODO:
    }
}
