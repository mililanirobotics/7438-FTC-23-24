package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.aSClib.SubsystemBase;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;

public class MecanumSubsystem implements SubsystemBase {
    private DcMotorEx leftFrontDrive;
    private DcMotorEx rightFrontDrive;
    private DcMotorEx leftBackDrive;
    private DcMotorEx rightBackDrive;

    private BNO055IMU imu;
    private final BNO055IMU.Parameters parameters;

    private boolean slowModeOn;

    public MecanumSubsystem(HardwareMap hwMap, Telemetry telemetry) {
        //Initializing Motors
        leftFrontDrive = hwMap.get(DcMotorEx.class, "leftFrontDrive");
        rightFrontDrive = hwMap.get(DcMotorEx.class, "rightFrontDrive");
        leftBackDrive = hwMap.get(DcMotorEx.class, "leftBackDrive");
        rightBackDrive = hwMap.get(DcMotorEx.class, "rightBackDrive");

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontDrive.setDirection(Constants.MecanumConstants.leftFrontReverse);
        leftBackDrive.setDirection(Constants.MecanumConstants.leftBackReverse);
        rightFrontDrive.setDirection(Constants.MecanumConstants.rightFrontReverse);
        rightBackDrive.setDirection(Constants.MecanumConstants.rightBackReverse);

        imu = hwMap.get(BNO055IMU.class, "imu");
        parameters = new BNO055IMU.Parameters();
        imu.initialize(parameters);

        telemetry.addData("Status", "Mecanum Drive Initializes");
        telemetry.update();
    }

    // Control method for mecanum drive during Tele-Op
    public void operate(Gamepad gamepad) {
        double y = -gamepad.left_stick_y;
        double x = gamepad.left_stick_x;
        double rx = gamepad.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        if (gamepad.left_bumper && !slowModeOn) {
            slowModeOn = true;
        }
        else if (gamepad.left_bumper && slowModeOn){
            slowModeOn = false;
        }

        if (slowModeOn) {
            leftFrontDrive.setPower((y + x + rx) / denominator * 0.5);
            rightFrontDrive.setPower((y - x - rx) / denominator * 0.5);
            leftBackDrive.setPower((y - x + rx) / denominator * 0.5);
            rightBackDrive.setPower((y + x - rx) / denominator * 0.5);
        }
        else {
            leftFrontDrive.setPower((y + x + rx) / denominator);
            rightFrontDrive.setPower((y - x - rx) / denominator);
            leftBackDrive.setPower((y - x + rx) / denominator);
            rightBackDrive.setPower((y + x - rx) / denominator);
        }
    }

    // Returns the current encoder count reading.
    public double[] encoderReading () {
        double[] encoderReading = new double[4];

        encoderReading[0] = leftFrontDrive.getCurrentPosition();
        encoderReading[1] = rightFrontDrive.getCurrentPosition();
        encoderReading[2] = leftBackDrive.getCurrentPosition();
        encoderReading[3] = rightBackDrive.getCurrentPosition();

        return encoderReading;
    }

    // Sets the counts target for encoders including directional targets.
    public void setEncoderTarget (int targetCounts, String direction) {
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction.equals("Forwards")) {
            leftFrontDrive.setTargetPosition(targetCounts);
            rightFrontDrive.setTargetPosition(targetCounts);
            leftBackDrive.setTargetPosition(targetCounts);
            rightBackDrive.setTargetPosition(targetCounts);
        }
        else if (direction.equals("Backwards")) {
            leftFrontDrive.setTargetPosition(-targetCounts);
            rightFrontDrive.setTargetPosition(-targetCounts);
            leftBackDrive.setTargetPosition(-targetCounts);
            rightBackDrive.setTargetPosition(-targetCounts);
        }
        else if (direction.equals("Left")) {
            leftFrontDrive.setTargetPosition(-targetCounts);
            rightFrontDrive.setTargetPosition(targetCounts);
            leftBackDrive.setTargetPosition(targetCounts);
            rightBackDrive.setTargetPosition(-targetCounts);
        }
        else if (direction.equals("Right")) {
            leftFrontDrive.setTargetPosition(targetCounts);
            rightFrontDrive.setTargetPosition(-targetCounts);
            leftBackDrive.setTargetPosition(-targetCounts);
            rightBackDrive.setTargetPosition(targetCounts);
        }

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    // Sets the drive motors to a given power
    public void setPower (double leftPower, double rightPower) {
        leftFrontDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        leftBackDrive.setPower(leftPower);
        rightBackDrive.setPower(rightPower);
    }

    public void autoStrafePower (double leftPower, double rightPower) {
        leftFrontDrive.setPower(-leftPower);
        rightFrontDrive.setPower(rightPower);
        leftBackDrive.setPower(leftPower);
        rightBackDrive.setPower(-rightPower);
    }

    // Returns the Z-axis rotational angle
    public double getOrientation() {
        return imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).thirdAngle;
    }

    // A check statement for waiting until the drive motors stop moving.
    public boolean isBusyCheck() {
        boolean isBusy = true;
        if (leftFrontDrive.isBusy() == true || rightFrontDrive.isBusy() == true || leftBackDrive.isBusy() == true || rightBackDrive.isBusy() == true) {

        }
        else {
            isBusy = false;
        }
        return isBusy;
    }

    // Sets all motor power to zero
    public void shutdown() {
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
}