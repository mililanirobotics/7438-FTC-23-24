package org.firstinspires.ftc.teamcode.aSClib;

import com.qualcomm.robotcore.hardware.Gamepad;

public class ControllerBinding {
    private CommandScheduler commandScheduler;
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;
    private int bindedKey;
    private double triggerTolerance;

    public ControllerBinding(Gamepad gamepad, int bindedKey, CommandScheduler commandScheduler) {
        this.gamepad = gamepad;
        this.bindedKey = bindedKey;
        this.commandScheduler = commandScheduler;

        controllerAddons = new ControllerAddons();
    }

    public ControllerBinding setTriggerTolerance(double triggerTolerance) {
        this.triggerTolerance = triggerTolerance;
        return this;
    }

    public boolean checkBinding() {
        return controllerAddons.getButton(gamepad, bindedKey) || controllerAddons.getTrigger(gamepad, bindedKey) > triggerTolerance;
    }

    public void onTrue(CommandBase command) {
        if (checkBinding()) {
            commandScheduler.addCommand(command);
        }
    }
}