package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.AprilTagSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;
import org.firstinspires.ftc.teamcode.aSClib.ControllerAddons;

public class AlignCommand implements CommandBase {
    private MecanumSubsystem s_MecanumSubsystem;
    private AprilTagSubsystem s_AprilTagSubsystem;
    private PIDController alignPID;
    private double power;

    public AlignCommand(MecanumSubsystem s_MecanumSubsystem, AprilTagSubsystem s_AprilTagSubsystem) {
        s_MecanumSubsystem = this.s_MecanumSubsystem;
        s_AprilTagSubsystem = this.s_AprilTagSubsystem;

        alignPID = new PIDController(
                Constants.MecanumConstants.kPController,
                Constants.MecanumConstants.kIController,
                Constants.MecanumConstants.kDController,
                Constants.MecanumConstants.kFFController,
                Constants.MecanumConstants.kILController);
    }

    @Override
    public void initialize() {
        alignPID.createSetPoint(0);
    }

    @Override
    public void execute() {
        alignPID.setProcessVariable(s_AprilTagSubsystem.getXOffset());
        power = alignPID.getOutput();
        s_MecanumSubsystem.setPower(power, -power);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(s_AprilTagSubsystem.getXOffset()) < 1;
    }
}
