package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.CascadeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TensorflowSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Commands.Cascade.*;
import org.firstinspires.ftc.teamcode.ControllerBinding;

import org.firstinspires.ftc.teamcode.Constants.OperatorConstants;

import java.util.HashMap;

public class RobotContainer {
    private OpMode opMode;
    private Telemetry telemetry;
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;
    private HardwareMap hwMap;

    private MecanumSubsystem mecanumSubsystem;
    private CascadeSubsystem cascadeSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private AprilTagSubsystem aprilTagSubsystem;
    private TensorflowSubsystem tensorflowSubsystem;

    public RobotContainer(OpMode opMode, Telemetry telemetry) {
        this.opMode = opMode;
        this.telemetry = telemetry;
        gamepad = opMode.gamepad1;
        hwMap = opMode.hardwareMap;

        mecanumSubsystem = new MecanumSubsystem(opMode, telemetry);
        cascadeSubsystem = new CascadeSubsystem(opMode);
        intakeSubsystem = new IntakeSubsystem(opMode);
        aprilTagSubsystem = new AprilTagSubsystem(opMode, telemetry);
        tensorflowSubsystem = new TensorflowSubsystem(opMode, telemetry);
        controllerAddons = new ControllerAddons();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void configureButtonBindings() {
        new ControllerBinding(gamepad, OperatorConstants.kLEFTBUMPER)
                .onTrue(new LiftAscensionCommand(gamepad, cascadeSubsystem));
    }

    public void shutdown() {
        mecanumSubsystem.shutdown();
        cascadeSubsystem.shutdown();
        intakeSubsystem.shutdown();
        aprilTagSubsystem.shutdown();
        tensorflowSubsystem.shutdown();
    }

    public void run() {
        mecanumSubsystem.run();
        cascadeSubsystem.run();
        intakeSubsystem.run();
        aprilTagSubsystem.run();
        tensorflowSubsystem.run();
    }
}
