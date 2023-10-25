package org.firstinspires.ftc.teamcode.Commands.Cascade;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.Constants.OperatorConstants;
import org.firstinspires.ftc.teamcode.Subsystems.CascadeSubsystem;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;
import org.firstinspires.ftc.teamcode.aSClib.ControllerAddons;

public class LiftAscensionCommand implements CommandBase {
    private CascadeSubsystem s_CascadeSubsystem;
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;

    public LiftAscensionCommand(Gamepad gamepad, CascadeSubsystem s_CascadeSubsystem) {
        this.s_CascadeSubsystem = s_CascadeSubsystem;
        this.controllerAddons = controllerAddons;

        controllerAddons = new ControllerAddons();
    }

    @Override
    public void execute() {
        if (controllerAddons.getButton(gamepad, OperatorConstants.kLEFTBUMPER)) {
            s_CascadeSubsystem.setLift(1);
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
        return !controllerAddons.getButton(gamepad, OperatorConstants.kLEFTBUMPER);
    }
}
