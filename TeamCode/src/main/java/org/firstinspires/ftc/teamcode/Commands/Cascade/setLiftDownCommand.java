package org.firstinspires.ftc.teamcode.Commands.Cascade;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Constants.CascadeConstants;
import org.firstinspires.ftc.teamcode.Subsystems.CascadeSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;

public class setLiftDownCommand implements CommandBase {
    private CascadeSubsystem s_CascadeSubsystem;
    private PIDController pidController;
    private Gamepad gamepad;

    private double currentPosition;
    private double desiredPosition;
    private double error;

    public setLiftDownCommand(Gamepad gamepad, CascadeSubsystem s_CascadeSubsystem) {
        this.s_CascadeSubsystem = s_CascadeSubsystem;
        this.gamepad = gamepad;

//        pidController = new PIDController(
//                Constants.MecanumConstants.kPController,
//                Constants.MecanumConstants.kIController,
//                Constants.MecanumConstants.kDController,
//                Constants.MecanumConstants.kFFController,
//                Constants.MecanumConstants.kILController);
    }

    @Override
    public void initialize() {
        error = CascadeConstants.stowPosition - s_CascadeSubsystem.getLiftPosition();
    }

    @Override
    public void execute() {
        error = CascadeConstants.stowPosition - s_CascadeSubsystem.getLiftPosition();
        if (error > 0) {
            s_CascadeSubsystem.setLift(1);
        }
        else {
            s_CascadeSubsystem.setLift(-1);
        }
    }

    @Override
    public void end() {
        s_CascadeSubsystem.shutdown();
    }

    @Override
    public boolean isFinished() {
        return Math.abs(error) < CascadeConstants.liftTolerance;
    }
}
