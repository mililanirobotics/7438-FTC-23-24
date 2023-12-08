package org.firstinspires.ftc.teamcode.Commands.Intake;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.aSClib.CommandBase;
import org.firstinspires.ftc.teamcode.aSClib.ControllerAddons;

public class IntakeCommand implements CommandBase {
    private IntakeSubsystem s_IntakeSubsystem;
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;

    public IntakeCommand(Gamepad gamepad, IntakeSubsystem s_IntakeSubsystem) {
        this.s_IntakeSubsystem = s_IntakeSubsystem;
        this.controllerAddons = controllerAddons;

        controllerAddons = new ControllerAddons();
    }

    @Override
    public void execute() {
        if (controllerAddons.getButton(gamepad, Constants.OperatorConstants.kRIGHTBUMPER)) {
            s_IntakeSubsystem.setIntake(0);
        }
        else if (controllerAddons.getButton(gamepad, Constants.OperatorConstants.kLEFTBUMPER)) {
            s_IntakeSubsystem.setIntake(1);
        }
        else {
            s_IntakeSubsystem.setIntake(0.5);
        }


        if (controllerAddons.getAnalog(gamepad, Constants.OperatorConstants.kLEFTSTICKY) > 0.1) {
            s_IntakeSubsystem.setRacket(1);
        }
        else if (controllerAddons.getAnalog(gamepad, Constants.OperatorConstants.kLEFTSTICKY) < -0.1) {
            s_IntakeSubsystem.setRacket(0);
        }
        else {
            s_IntakeSubsystem.setRacket(0.5);
        }
    }

    @Override
    public void end() {
        s_IntakeSubsystem.shutdown();
    }

    @Override
    public boolean isFinished() {
        return !controllerAddons.getButton(gamepad, Constants.OperatorConstants.kRIGHTBUMPER);
    }
}
