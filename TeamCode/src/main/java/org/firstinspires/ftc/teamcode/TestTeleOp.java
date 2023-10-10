package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;

@TeleOp(name="Official TeleOp")

public class TestTeleOp extends OpMode
{
    // Declare OpMode members.
    private HardwareMap hwMap = this.hardwareMap;
    private MecanumSubsystem mecanumSubsystem;
    private DriveCommand driveCommand;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {
        mecanumSubsystem = new MecanumSubsystem(this, telemetry);
        new DriveCommand(gamepad1, mecanumSubsystem)
                .run();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() {
        driveCommand.run();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        mecanumSubsystem.shutdown();
    }

}