package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem {
    private DcMotor intake;
    private Servo servo;

    public IntakeSubsystem(OpMode opMode) {
        intake = opMode.hardwareMap.get(DcMotor.class, "intake");
        servo = opMode.hardwareMap.get(Servo.class, "servo");

        intake.setDirection(Constants.IntakeConstants.intake);

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setIntake(double power) {
        intake.setPower(power);
    }

    public void setServoIntake() {
        servo.setPosition(Constants.IntakeConstants.servoIntakePosition);
    }

    public void setServoDelivery() {
        servo.setPosition(Constants.IntakeConstants.servoDeliverPosition);
    }
}
