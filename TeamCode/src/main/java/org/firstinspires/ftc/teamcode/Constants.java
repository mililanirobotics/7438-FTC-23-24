package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class Constants {
    public static class MecanumConstants {
        public static double slowModeTrigger = 0.5;
        public static double slowModeScaler = 0.5;

        public static DcMotorSimple.Direction leftFrontReverse = DcMotorSimple.Direction.FORWARD;
        public static DcMotorSimple.Direction leftBackReverse = DcMotorSimple.Direction.REVERSE;
        public static DcMotorSimple.Direction rightFrontReverse = DcMotorSimple.Direction.FORWARD;
        public static DcMotorSimple.Direction rightBackReverse = DcMotorSimple.Direction.FORWARD;

        public static double kPController = 0;
        public static double kIController = 0;
        public static double kDController = 0;
        public static double kFFController = 0;
        public static double kILController = 0;
    }

    public static class VisionSubsystem {

    }

    public static class IntakeConstants {
        public static DcMotorSimple.Direction intake = DcMotorSimple.Direction.FORWARD;

        public static double servoIntakePosition = 0;
        public static double servoDeliverPosition = 0;
    }

    public static class CascadeConstants {
        public static DcMotorSimple.Direction leftLift = DcMotorSimple.Direction.FORWARD;
        public static DcMotorSimple.Direction rightLift = DcMotorSimple.Direction.REVERSE;
    }
}