package org.firstinspires.ftc.teamcode.aSClib;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class CommandScheduler {
    private HashMap<List<SubsystemBase>, CommandBase> commands = new HashMap<List<SubsystemBase>, CommandBase>();
    private List<CommandBase> runningCommands = new ArrayList<>();
    public List<CommandBase> defaultCommands = new ArrayList<CommandBase>();
    private List<SubsystemBase> currentRequirements = new ArrayList<SubsystemBase>();
    private List<SubsystemBase> subsystems = new ArrayList<SubsystemBase>();

    private List<ButtonBinding> buttonBindings = new ArrayList<>();
    private List<AnalogBinding> analogBindings = new ArrayList<>();

    public CommandScheduler(SubsystemBase... subsystems) {
        for (SubsystemBase i : subsystems) {
            this.subsystems.add(i);
        }
    }

    public CommandScheduler configureDigitalBindings(ButtonBinding... buttonBindings) {
        for (ButtonBinding i : buttonBindings) {
            this.buttonBindings.add(i);
        }
        return this;
    }

    public CommandScheduler configureAnalogBindings(AnalogBinding... analogBindings) {
        for (AnalogBinding i : analogBindings) {
            this.analogBindings.add(i);
        }
        return this;
    }

    public void setDefaultCommand(CommandBase command) {
        defaultCommands.add(command);
    }

    public void addCommand(CommandBase command) {
        command.initialize();
        commands.put(command.getRequirements(), command);
    }

    public void queueCommand(CommandBase command) {
        runningCommands.add(command);
    }

    private void checkCommand(CommandBase command) {
        if (command.isFinished()) {
            commands.remove(command);
            for (SubsystemBase i : command.getRequirements()) {
                currentRequirements.remove(i);
            }
        }

    }

    private void addRequirements(List<SubsystemBase> requirements) {
        for (SubsystemBase i : requirements) {
            currentRequirements.add(i);
        }
    }

    private boolean checkRequirements(List<SubsystemBase> requirements) {
        for (SubsystemBase i : requirements) {
            if (currentRequirements.contains(i)) {
                return true;
            }
        }
        return false;
    }

    private void runCommands() {
        for (CommandBase i : commands.values()) {
            checkCommand(i);
            if (runningCommands.contains(i)) {
                i.run();
                continue;
            }
            if (!checkRequirements(i.getRequirements())) {
                queueCommand(i);
                i.run();
                addRequirements(i.getRequirements());
            }
        }
    }

    private void runDefaultCommands() {
        for (CommandBase i : defaultCommands) {
            i.run();
        }
    }

    private void runPeriodic() {
        for (SubsystemBase i : subsystems) {
            i.periodic();
        }
    }

    private void runBindings() {
        for (ButtonBinding i : buttonBindings) {
            i.run();
        }

        for (AnalogBinding i : analogBindings) {
            i.run();
        }
    }

    public void shutdown() {
        for (SubsystemBase i : subsystems) {
            i.shutdown();
        }
    }

    public void run() {
        runBindings();
        runCommands();
        runDefaultCommands();
        runPeriodic();
    }
}