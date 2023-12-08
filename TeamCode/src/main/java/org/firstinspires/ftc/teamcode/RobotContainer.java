package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Cascade.LiftCommand;
import org.firstinspires.ftc.teamcode.Commands.Drivetrain.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.Drivetrain.GyroTurnCommand;
import org.firstinspires.ftc.teamcode.Commands.Drivetrain.MoveDistanceCommand;
import org.firstinspires.ftc.teamcode.Commands.Drivetrain.StrafeDistanceCommand;
import org.firstinspires.ftc.teamcode.Commands.Cascade.AutoLiftCommand;
import org.firstinspires.ftc.teamcode.Commands.Cascade.LiftDescensionCommand;
import org.firstinspires.ftc.teamcode.Commands.Cascade.LiftAscensionCommand;

import org.firstinspires.ftc.teamcode.Commands.Intake.IntakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.CascadeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.TensorflowSubsystem;

import org.firstinspires.ftc.teamcode.aSClib.CommandBase;
import org.firstinspires.ftc.teamcode.aSClib.CommandScheduler;
import org.firstinspires.ftc.teamcode.aSClib.ConditionalCommand;
import org.firstinspires.ftc.teamcode.aSClib.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.aSClib.ButtonBinding;

import org.firstinspires.ftc.teamcode.Constants.OperatorConstants;
import org.firstinspires.ftc.teamcode.Constants.VisionConstants;

public class RobotContainer {
    private OpMode opMode;
    private Telemetry telemetry;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

//    private final MecanumSubsystem mecanumSubsystem = new MecanumSubsystem(opMode, telemetry);
//    private final CascadeSubsystem cascadeSubsystem = new CascadeSubsystem(opMode);
//    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem(opMode, telemetry);
//    private final AprilTagSubsystem aprilTagSubsystem = new AprilTagSubsystem(opMode, telemetry);
    private final TensorflowSubsystem tensorflowSubsystem = new TensorflowSubsystem(opMode, telemetry);

    private final CommandScheduler scheduler = new CommandScheduler(
//            mecanumSubsystem,
//            cascadeSubsystem,
//            intakeSubsystem,
//            aprilTagSubsystem,
            tensorflowSubsystem
    );

    public RobotContainer(OpMode opMode, Telemetry telemetry) {
        this.opMode = opMode;
        this.telemetry = telemetry;
        gamepad1 = opMode.gamepad1;
        gamepad2 = opMode.gamepad2;
//
//        mecanumSubsystem.setDefaultCommand(scheduler, new DriveCommand(gamepad1, telemetry, mecanumSubsystem));
//        cascadeSubsystem.setDefaultCommand(scheduler, new LiftCommand(gamepad2, cascadeSubsystem));
//        intakeSubsystem.setDefaultCommand(scheduler, new IntakeCommand(gamepad2, intakeSubsystem));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public void configureButtonBindings() {

//        scheduler.configureDigitalBindings(
//                new ButtonBinding(gamepad, OperatorConstants.kLEFTBUMPER, scheduler)
//                        .onTrue(new LiftAscensionCommand(gamepad, cascadeSubsystem)),
//                new ButtonBinding(gamepad, OperatorConstants.kRIGHTBUMPER, scheduler)
//                        .onTrue(new LiftDescensionCommand(gamepad, cascadeSubsystem))
//        );
    }

//    public CommandBase redRightPath() {
//        switch(tensorflowSubsystem.objectPosition()) {
//            case NONE:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 5, telemetry),
//                        new StrafeDistanceCommand(mecanumSubsystem, 44)
//                );
//            case LEFT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 10, telemetry),
//                        new GyroTurnCommand(mecanumSubsystem, 90, telemetry),
//                        new MoveDistanceCommand(mecanumSubsystem, 50, telemetry)
//                );
//            case CENTER:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 1, telemetry)
//                );
//            case RIGHT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 12, telemetry)
//                );
//        }
//        return null;
//    }
//
//    public CommandBase redLeftPath() {
//        switch(tensorflowSubsystem.objectPosition()) {
//            case NONE:
//                break;
//            case LEFT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 10, telemetry),
//                        new GyroTurnCommand(mecanumSubsystem, 90, telemetry),
//                        new MoveDistanceCommand(mecanumSubsystem, 10, telemetry)
//                );
//            case CENTER:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 1, telemetry)
//                );
//            case RIGHT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 12, telemetry)
//                );
//        }
//        return null;
//    }
//
//    public CommandBase blueRightPath() {
//        switch(tensorflowSubsystem.objectPosition()) {
//            case NONE:
//                break;
//            case LEFT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 10, telemetry),
//                        new GyroTurnCommand(mecanumSubsystem, 90, telemetry),
//                        new MoveDistanceCommand(mecanumSubsystem, 20, telemetry)
//                );
//            case CENTER:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 1, telemetry)
//                );
//            case RIGHT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 12, telemetry)
//                );
//        }
//        return null;
//    }
//
//    public CommandBase blueLeftPath() {
//        switch(tensorflowSubsystem.objectPosition()) {
//            case NONE:
//                break;
//            case LEFT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 10, telemetry),
//                        new GyroTurnCommand(mecanumSubsystem, 90, telemetry),
//                        new MoveDistanceCommand(mecanumSubsystem, 4, telemetry)
//                );
//            case CENTER:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 1, telemetry)
//                );
//            case RIGHT:
//                return new SequentialCommandGroup(
//                        new MoveDistanceCommand(mecanumSubsystem, 12, telemetry)
//                );
//        }
//        return null;
//    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public void run() {
        scheduler.run();
    }
}
