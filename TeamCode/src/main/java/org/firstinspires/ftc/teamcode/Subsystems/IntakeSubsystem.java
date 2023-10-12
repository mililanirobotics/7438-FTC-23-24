package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem implements SubsystemBase {
    private DcMotor intake;
    private Servo servo;

    public IntakeSubsystem(OpMode opMode) {
        intake = opMode.hardwareMap.get(DcMotor.class, "intake");
        servo = opMode.hardwareMap.get(Servo.class, "servo");

        intake.setDirection(IntakeConstants.intake);

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setIntake(double power) {
        intake.setPower(power);
    }

    public void setServoIntake() {
        servo.setPosition(IntakeConstants.servoIntakePosition);
    }

    public void setServoDelivery() {
        servo.setPosition(IntakeConstants.servoDeliverPosition);
    }

    @Override
    public void shutdown() {
        intake.setPower(0);
        servo.setPosition(IntakeConstants.servoIntakePosition);
    }

    @Override
    public void periodic() {}
}
