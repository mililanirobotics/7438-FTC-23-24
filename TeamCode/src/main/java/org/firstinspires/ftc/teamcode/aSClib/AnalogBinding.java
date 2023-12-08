package org.firstinspires.ftc.teamcode.aSClib;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.ArrayList;
import java.util.List;

public class AnalogBinding {
    private CommandScheduler commandScheduler;
    private ControllerAddons controllerAddons;

    private List<CommandBase> onTrueCommands = new ArrayList<>();
    private List<CommandBase> onFalseCommands = new ArrayList<>();

    private Gamepad gamepad;
    private int bindedKey;
    private double triggerTolerance;

    public AnalogBinding(Gamepad gamepad, int bindedKey, double triggerTolerance, CommandScheduler commandScheduler) {
        this.gamepad = gamepad;
        this.bindedKey = bindedKey;
        this.triggerTolerance = triggerTolerance;
        this.commandScheduler = commandScheduler;

        controllerAddons = new ControllerAddons();
    }

    public AnalogBinding onTrue(CommandBase... commands) {
        for (CommandBase i : commands) {
            onTrueCommands.add(i);
        }
        return this;
    }

    public AnalogBinding onFalse(CommandBase... commands) {
        for (CommandBase i : commands) {
            onFalseCommands.add(i);
        }
        return this;
    }

    public void run() {
        if (Math.abs(controllerAddons.getAnalog(gamepad, bindedKey)) > triggerTolerance) {
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
