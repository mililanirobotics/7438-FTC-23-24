package org.firstinspires.ftc.teamcode.Commands.Drivetrain;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;
import org.firstinspires.ftc.teamcode.aSClib.ControllerAddons;

public class DriveCommand implements CommandBase {
    private Gamepad gamepad;
    private Telemetry telemetry;
    private MecanumSubsystem s_MecanumSubsystem;

    double y, x, theta;
    double heading, rotX, rotY, denominator;
    double leftFrontPower, leftBackPower, rightFrontPower, rightBackPower;

    public DriveCommand(Gamepad gamepad, Telemetry telemetry, MecanumSubsystem s_MecanumSubsystem) {
        this.gamepad = gamepad;
        this.telemetry = telemetry;
        this.s_MecanumSubsystem = s_MecanumSubsystem;
    }

    @Override
    public void execute() {
        y = -gamepad.left_stick_y;
        x = gamepad.left_stick_x;
        theta = gamepad.right_stick_x;

        heading = s_MecanumSubsystem.getOrientation();

        rotX = x * Math.cos(-heading) - y * Math.sin(-heading);
        rotY = x * Math.sin(-heading) + y * Math.cos(-heading);
        denominator = Math.max(Math.abs(rotX) + Math.abs(rotY) + Math.abs(theta), 1);

        leftFrontPower = (rotY + rotX + theta) / denominator;
        leftBackPower = (rotY - rotX + theta) / denominator;
        rightFrontPower = (rotY - rotX - theta) / denominator;rightBackPower = (rotY + rotX - theta) / denominator;

        if(gamepad.right_trigger >= Constants.MecanumConstants.slowModeTrigger) {
            s_MecanumSubsystem.leftPower(
                    leftFrontPower * Constants.MecanumConstants.slowModeScaler,
                    leftBackPower * Constants.MecanumConstants.slowModeScaler
            );
            s_MecanumSubsystem.rightPower(
                    rightFrontPower * Constants.MecanumConstants.slowModeScaler,
                    rightBackPower * Constants.MecanumConstants.slowModeScaler
            );
        }
        else {
            s_MecanumSubsystem.leftPower(
                    leftFrontPower,
                    leftBackPower
            );
            s_MecanumSubsystem.rightPower(
                    rightFrontPower,
                    rightBackPower
            );
        }

        telemetry.addData("Left Front Power: ", leftFrontPower);
        telemetry.addData("Left Back Power: ", leftBackPower);
        telemetry.addData("Right Front Power: ", rightFrontPower);
        telemetry.addData("Right Back Power: ", rightBackPower);
        telemetry.addData("Robot Heading: ", heading);
        telemetry.addData("Y-axis", rotY);
        telemetry.update();
    }

    @Override
    public void end() {
        s_MecanumSubsystem.shutdown();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
