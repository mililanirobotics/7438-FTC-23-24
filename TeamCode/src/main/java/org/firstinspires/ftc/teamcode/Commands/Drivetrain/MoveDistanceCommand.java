package org.firstinspires.ftc.teamcode.Commands.Drivetrain;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;

public class MoveDistanceCommand implements CommandBase {
    private Telemetry telemetry;
    private MecanumSubsystem mecanumSubsystem;
    private PIDController pidController;
    private double initialDistance;
    private double targetDistance;
    private double distanceInInches;
    private double power;


    public MoveDistanceCommand(MecanumSubsystem mecanumSubsystem, double distanceInInches, Telemetry telemetry) {
        this.distanceInInches = distanceInInches;
        this.telemetry = telemetry;

        pidController = new PIDController(
                Constants.MecanumConstants.kPController,
                Constants.MecanumConstants.kIController,
                Constants.MecanumConstants.kDController,
                Constants.MecanumConstants.kFFController,
                Constants.MecanumConstants.kILController
        );

        this.mecanumSubsystem = mecanumSubsystem;
        addRequirement(mecanumSubsystem);
    }

    @Override
    public void initialize() {
        initialDistance = mecanumSubsystem.getCurrentPosition();
        targetDistance = initialDistance + distanceInInches;

        pidController.createSetPoint(targetDistance);
        pidController.setProcessVariable(initialDistance);
    }

    @Override
    public void execute() {
        pidController.setProcessVariable(mecanumSubsystem.getCurrentPosition());
        power = pidController.getOutput();
        mecanumSubsystem.setPower(power, power);

        // telemetry.addData("Current", mecanumSubsystem.getCurrentPosition());
        // telemetry.addData("Target", targetDistance);
        // telemetry.addData("power", power);
        // telemetry.addData("error", pidController.getError());
        // telemetry.update();
    }

    @Override
    public void end() {
        mecanumSubsystem.setPower(0,0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(pidController.getError()) < 1 ;
    }
}
