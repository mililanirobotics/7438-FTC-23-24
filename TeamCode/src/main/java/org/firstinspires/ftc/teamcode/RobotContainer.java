package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Subsystems.CascadeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TensorflowSubsystem;
import org.firstinspires.ftc.teamcode.CommandScheduler;
import org.firstinspires.ftc.teamcode.Commands.Cascade.*;
import org.firstinspires.ftc.teamcode.ControllerBinding;

import org.firstinspires.ftc.teamcode.Constants.OperatorConstants;

public class RobotContainer {
    private OpMode opMode;
    private Telemetry telemetry;
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;

    private MecanumSubsystem mecanumSubsystem;
    private CascadeSubsystem cascadeSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private AprilTagSubsystem aprilTagSubsystem;
    private TensorflowSubsystem tensorflowSubsystem;

    private CommandScheduler scheduler;

    public RobotContainer(OpMode opMode, Telemetry telemetry) {
        this.opMode = opMode;
        this.telemetry = telemetry;
        gamepad = opMode.gamepad1;

        mecanumSubsystem = new MecanumSubsystem(opMode, telemetry);
        cascadeSubsystem = new CascadeSubsystem(opMode);
        intakeSubsystem = new IntakeSubsystem(opMode);
        aprilTagSubsystem = new AprilTagSubsystem(opMode, telemetry);
        tensorflowSubsystem = new TensorflowSubsystem(opMode, telemetry);
        controllerAddons = new ControllerAddons();

        scheduler = new CommandScheduler()
                .addSubsystem(mecanumSubsystem)
                .addSubsystem(cascadeSubsystem)
                .addSubsystem(intakeSubsystem)
                .addSubsystem(aprilTagSubsystem)
                .addSubsystem(tensorflowSubsystem);

        mecanumSubsystem.setDefaultCommand(new DriveCommand(gamepad, mecanumSubsystem));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void configureButtonBindings() {
//        new ControllerBinding(gamepad, OperatorConstants.kLEFTBUMPER, scheduler)
//                .onTrue(new LiftAscensionCommand(gamepad, cascadeSubsystem));
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public void run() {
        scheduler.run();
    }
}
