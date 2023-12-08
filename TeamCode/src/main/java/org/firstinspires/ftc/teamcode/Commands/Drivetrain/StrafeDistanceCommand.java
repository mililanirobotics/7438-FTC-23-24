package org.firstinspires.ftc.teamcode.Commands.Drivetrain;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;

public class StrafeDistanceCommand implements CommandBase {
    private MecanumSubsystem mecanumSubsystem;
    private PIDController pidController;
    private double initialDistance;
    private double targetDistance;
    private double distanceInInches;
    private double power;


    public StrafeDistanceCommand(MecanumSubsystem mecanumSubsystem, double distanceInInches) {
        this.distanceInInches = distanceInInches;

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
        mecanumSubsystem.strafePower(power, power);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(pidController.getError()) < 1 ;
    }
}
