package org.firstinspires.ftc.teamcode.aSClib;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.ArrayList;
import java.util.List;

public class ButtonBinding {
    private CommandScheduler commandScheduler;
    private ControllerAddons controllerAddons;

    private List<CommandBase> onTrueCommands = new ArrayList<>();
    private List<CommandBase> onFalseCommands = new ArrayList<>();

    private Gamepad gamepad;
    private int bindedKey;

    public ButtonBinding(Gamepad gamepad, int bindedKey, CommandScheduler commandScheduler) {
        this.gamepad = gamepad;
        this.bindedKey = bindedKey;
        this.commandScheduler = commandScheduler;

        controllerAddons = new ControllerAddons();
    }

    public ButtonBinding onTrue(CommandBase command) {
        onTrueCommands.add(command);
        return this;
    }

    public ButtonBinding onFalse(CommandBase command) {
        onFalseCommands.add(command);
        return this;
    }

    public void run() {
        if (controllerAddons.getButton(gamepad, bindedKey)) {
            for (CommandBase i : onTrueCommands) {
                commandScheduler.addCommand(i);
            }
        }
        else {
            for (CommandBase i : onFalseCommands) {
                commandScheduler.addCommand(i);
            }
        }
    }

}
