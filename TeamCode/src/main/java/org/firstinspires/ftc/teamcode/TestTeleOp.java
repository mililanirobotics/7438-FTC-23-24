package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

@TeleOp(name="Official TeleOp")

public class TestTeleOp extends OpMode
{
    // Declare OpMode members.
    private HardwareMap hwMap = this.hardwareMap;
    private MecanumSubsystem mecanumSubsystem;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {
        mecanumSubsystem = new MecanumSubsystem(this, telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {
        mecanumSubsystem.drive(gamepad1);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        mecanumSubsystem.shutdown();
    }

}