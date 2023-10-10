package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.DcMotor;

public class CascadeSubsystem {
    private DcMotor leftLift;
    private DcMotor rightLift;

    public CascadeSubsystem(OpMode opMode) {
        leftLift = opMode.hardwareMap.get(DcMotor.class, "leftLift");
        rightLift = opMode.hardwareMap.get(DcMotor.class, "rightLift");

        leftLift.setDirection(Constants.CascadeConstants.leftLift);
        rightLift.setDirection(Constants.CascadeConstants.rightLift);

        leftLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setLift(double power) {
        leftLift.setPower(power);
        rightLift.setPower(power);
    }

}
