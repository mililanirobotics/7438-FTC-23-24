package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.ControllerAddons;
import org.firstinspires.ftc.teamcode.Commands.CommandBase;

public class ControllerBinding extends Thread {
    private CommandBase command;
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;
    private int bindedKey;

    public ControllerBinding(Gamepad gamepad, int bindedKey) {
        this.gamepad = gamepad;
        this.bindedKey = bindedKey;

        controllerAddons = new ControllerAddons();
    }

    public void onTrue(CommandBase command) {
        this.command = command;
    }

    @Override
    public void run() {
        if (controllerAddons.get(gamepad, bindedKey)) {
            command.run();
        }
    }
}
