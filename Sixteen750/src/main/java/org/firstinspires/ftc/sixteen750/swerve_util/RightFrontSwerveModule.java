package org.firstinspires.ftc.sixteen750.swerve_util;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


@Config
public class RightFrontSwerveModule extends AnotherSwerveModule {
    public static PIDCoefficients RF_ROTATION_PID = new PIDCoefficients(0.6, 0, 0.03);
    public static CRServoProfiler.Constraints RF_SERVO_CONSTRAINTS = new CRServoProfiler.Constraints(1, 1000, 2);

    public RightFrontSwerveModule(DcMotorEx m, CRServo s, AbsoluteAnalogEncoder e) {
        super(m, s, e, RF_ROTATION_PID, RF_SERVO_CONSTRAINTS);
    }

    public RightFrontSwerveModule(HardwareMap hardwareMap, String motorName, String servoName, String encoderName){
        this(
                hardwareMap.get(DcMotorEx.class, motorName),
                hardwareMap.get(CRServo.class, servoName),
                new AbsoluteAnalogEncoder(hardwareMap.get(AnalogInput.class, encoderName))
        );
    }
}
