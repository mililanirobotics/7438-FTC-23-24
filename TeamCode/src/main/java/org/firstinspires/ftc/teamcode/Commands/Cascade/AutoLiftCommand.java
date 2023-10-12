package org.firstinspires.ftc.teamcode.Commands.Cascade;

import org.firstinspires.ftc.teamcode.Commands.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.CascadeSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;

public class AutoLiftCommand implements CommandBase {
    private CascadeSubsystem s_CascadeSubsystem;
    private PIDController pidController;
    private Gamepad gamepad;

    private double currentPosition;
    private double desiredPosition;
    private double error;

    public AutoLiftCommand(Gamepad gamepad, CascadeSubsystem s_CascadeSubsystem) {
        this.s_CascadeSubsystem = s_CascadeSubsystem;
        this.gamepad = gamepad;

        pidController = new PIDController(
                Constants.MecanumConstants.kPController,
                Constants.MecanumConstants.kIController,
                Constants.MecanumConstants.kDController,
                Constants.MecanumConstants.kFFController,
                Constants.MecanumConstants.kILController);
    }

    @Override
    public void initialize() {
        pidController.createSetPoint(Constants.CascadeConstants.deliveryPosition);
        pidController.setProcessVariable(s_CascadeSubsystem.getLiftPosition());
    }

    @Override
    public void execute() {
        s_CascadeSubsystem.setLift(pidController.getOutput());
    }

    @Override
    public void end(boolean interrupted) {
        s_CascadeSubsystem.shutdown();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(pidController.getError()) < Constants.CascadeConstants.liftTolerance;
    }
}
