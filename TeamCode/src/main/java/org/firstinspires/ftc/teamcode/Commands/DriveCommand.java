package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.Commands.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;

public class DriveCommand implements CommandBase {
    private Gamepad gamepad;
    private MecanumSubsystem s_MecanumSubsystem;

    public DriveCommand(Gamepad gamepad, MecanumSubsystem s_MecanumSubsystem) {
        this.gamepad = gamepad;
        this.s_MecanumSubsystem = s_MecanumSubsystem;
    }

    @Override
    public void execute() {
        s_MecanumSubsystem.drive(gamepad);
    }

    @Override
    public void end(boolean interrupted) {
        s_MecanumSubsystem.shutdown();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
