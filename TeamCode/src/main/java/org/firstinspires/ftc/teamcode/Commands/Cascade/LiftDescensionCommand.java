package org.firstinspires.ftc.teamcode.Commands.Cascade;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Commands.CommandBase;
import org.firstinspires.ftc.teamcode.Constants.OperatorConstants;
import org.firstinspires.ftc.teamcode.ControllerAddons;
import org.firstinspires.ftc.teamcode.Subsystems.CascadeSubsystem;

public class LiftDescensionCommand implements CommandBase {
    private CascadeSubsystem s_CascadeSubsystem;
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;

    public LiftDescensionCommand(Gamepad gamepad, CascadeSubsystem s_CascadeSubsystem) {
        this.s_CascadeSubsystem = s_CascadeSubsystem;
        this.controllerAddons = controllerAddons;

        controllerAddons = new ControllerAddons();
    }

    @Override
    public void execute() {
        if (controllerAddons.getButton(gamepad, OperatorConstants.kRIGHTBUMPER)) {
            s_CascadeSubsystem.setLift(-1);
        }
        else {
            s_CascadeSubsystem.setLift(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        s_CascadeSubsystem.shutdown();
    }

    @Override
    public boolean isFinished() {
        return !controllerAddons.getButton(gamepad, OperatorConstants.kRIGHTBUMPER);
    }
}
