package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Constants.MecanumConstants;
import org.firstinspires.ftc.teamcode.aSClib.SubsystemBase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class MecanumSubsystem implements SubsystemBase {
    //drive motors
    private final DcMotor leftFront;
    private final DcMotor leftBack;
    private final DcMotor rightFront;
    private final DcMotor rightBack;

    //control hub gyro
    private BNO055IMU imu;
    private final BNO055IMU.Parameters parameters;
    private Telemetry telemetry;

    public MecanumSubsystem(OpMode opMode, Telemetry telemetry) {
        this.telemetry = telemetry;
        //initializing motors
        leftFront = opMode.hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = opMode.hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = opMode.hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = opMode.hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(MecanumConstants.leftFrontReverse);
        leftBack.setDirection(MecanumConstants.leftBackReverse);
        rightFront.setDirection(MecanumConstants.rightFrontReverse);
        rightBack.setDirection(MecanumConstants.rightBackReverse);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //initializing gyro
        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);

        //feedback stating the drive is initialized
        telemetry.addData("Status", "Mecanum drive and gyro initialized");
        telemetry.update();
    }

    public void drive(Gamepad gamepad) {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double theta =gamepad.right_stick_x;

        double heading = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).thirdAngle;

        double rotX = x * Math.cos(-heading) - y * Math.sin(-heading);
        double rotY = x * Math.sin(-heading) + y * Math.cos(-heading);
        double denominator = Math.max(Math.abs(rotX) + Math.abs(rotY) + Math.abs(theta), 1);

        double leftFrontPower = (rotY + rotX + theta) / denominator;
        double leftBackPower = (rotY - rotX + theta) / denominator;
        double rightFrontPower = (rotY - rotX - theta) / denominator;
        double rightBackPower = (rotY + rotX - theta) / denominator;

        if(gamepad.right_trigger >= MecanumConstants.slowModeTrigger) {
            leftFront.setPower(leftFrontPower * MecanumConstants.slowModeScaler);
            leftBack.setPower(leftBackPower * MecanumConstants.slowModeScaler);
            rightFront.setPower(rightFrontPower * MecanumConstants.slowModeScaler);
            rightBack.setPower(rightBackPower * MecanumConstants.slowModeScaler);
        }
        else {
            leftFront.setPower(leftFrontPower);
            leftBack.setPower(leftBackPower);
            rightFront.setPower(rightFrontPower);
            rightBack.setPower(rightBackPower);
        }

        telemetry.addData("Left Front Power: ", leftFrontPower);
        telemetry.addData("Left Back Power: ", leftBackPower);
        telemetry.addData("Right Front Power: ", rightFrontPower);
        telemetry.addData("Right Back Power: ", rightBackPower);
        telemetry.addData("Robot Heading: ", heading);
        telemetry.addData("Y-axis", rotY);
        telemetry.update();
    }

    public void setPower(double leftPower, double rightPower) {
        leftFront.setPower(leftPower);
        leftBack.setPower(leftPower);
        rightFront.setPower(rightPower);
        rightBack.setPower(rightPower);
    }

    @Override
    public void shutdown() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }

    @Override
    public void periodic() {}
}