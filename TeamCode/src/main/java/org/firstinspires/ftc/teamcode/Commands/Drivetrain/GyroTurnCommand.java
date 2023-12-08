package org.firstinspires.ftc.teamcode.Commands.Drivetrain;

import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;

import org.firstinspires.ftc.teamcode.Constants.MecanumConstants;

public class GyroTurnCommand  implements CommandBase {
    private Telemetry telemetry;
    private MecanumSubsystem mecanumSubsystem;
    private PIDController pidController;
    private double initialOrientation;
    private double targetOrientation;
    private double turnDegrees;
    private double power;


    public GyroTurnCommand(MecanumSubsystem mecanumSubsystem, double turnDegrees, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.turnDegrees = turnDegrees;

        pidController = new PIDController(
                MecanumConstants.kPController,
                MecanumConstants.kIController,
                MecanumConstants.kDController,
                MecanumConstants.kFFController,
                MecanumConstants.kILController
        );

        this.mecanumSubsystem = mecanumSubsystem;
        addRequirement(mecanumSubsystem);
    }

    @Override
    public void initialize() {
        initialOrientation = mecanumSubsystem.getOrientation() * 180/Math.PI;
        targetOrientation = initialOrientation + turnDegrees;

        pidController.createSetPoint(targetOrientation);
        pidController.setProcessVariable(initialOrientation);
    }

    @Override
    public void execute() {
        pidController.setProcessVariable(mecanumSubsystem.getOrientation() * 180/Math.PI);
        power = pidController.getOutput();
        mecanumSubsystem.setPower(-power, power);

        telemetry.addData("BALLSACK", initialOrientation);
        telemetry.addData("BALLSACK", mecanumSubsystem.getOrientation() * 180/Math.PI);
        telemetry.update();
    };

    @Override
    public boolean isFinished() {
        return Math.abs(pidController.getError()) < 1 ;
    }
}
