package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.Constants.IntakeConstants;
import org.firstinspires.ftc.teamcode.aSClib.SubsystemBase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

public class IntakeSubsystem implements SubsystemBase {
    private CRServo leftRacket;
    private CRServo rightRacket;
    private CRServo leftIntake;
    private CRServo rightIntake;

    private Telemetry telemetry;

    public IntakeSubsystem(OpMode opMode, Telemetry telemetry) {
        leftRacket = opMode.hardwareMap.get(CRServo.class, "leftRacket");
        rightRacket = opMode.hardwareMap.get(CRServo.class, "rightRacket");
        leftIntake = opMode.hardwareMap.get(CRServo.class, "leftIntake");
        rightIntake = opMode.hardwareMap.get(CRServo.class, "rightIntake");

        rightIntake.setDirection(CRServo.Direction.REVERSE);
        rightRacket.setDirection(CRServo.Direction.REVERSE);
        this.telemetry = telemetry;
    }

    public void setIntake(double power) {
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

    public void setRacket(double power) {
        leftRacket.setPower(power);
        rightRacket.setPower(power);
    }

    @Override
    public void shutdown() {
        leftRacket.setPower(0.5);
        rightRacket.setPower(0.5);
        leftIntake.setPower(0.5);
        rightIntake.setPower(0.5);
    }

    @Override
    public void periodic() {
        // telemetry.addData("sdfsd", 9);
        // telemetry.update();

        // setIntake(1);
    }
}
