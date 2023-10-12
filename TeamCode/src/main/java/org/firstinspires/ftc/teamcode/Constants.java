package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class Constants {
    public static class OperatorConstants {
        public static int kABUTTON = 0;
        public static int kBBUTTON = 1;
        public static int kXBUTTON = 2;
        public static int kYBUTTON = 3;
        public static int kBACK = 4;
        public static int kSTART = 5;
        public static int kDPADDOWN = 6;
        public static int kDPADLEFT = 7;
        public static int kDPADRIGHT = 8;
        public static int kDPADUP = 9;
        public static int kLEFTBUMPER = 10;
        public static int kLEFTSTICKBUTTON = 11;
        public static int kLEFTSTICKX = 12;
        public static int kLEFTSTICKY = 13;
        public static int kLEFTTRIGGER = 14;
        public static int kRIGHTBUMPER = 15;
        public static int kRIGHTSTICKBUTTON = 16;
        public static int kRIGHTSTICKX = 17;
        public static int kRIGHTSTICKY = 18;
        public static int kRIGHTTRIGGER = 19;

        public static double triggerTolerance = 0.1;
    }
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

        public static int ticksPerRev = 23;

        public static double stowPosition = 0;
        public static double deliveryPosition = 0;
        public static double liftTolerance = 0;

        public static double kPController = 0;
        public static double kIController = 0;
        public static double kDController = 0;
        public static double kFFController = 0;
        public static double kILController = 0;
    }
}