package org.firstinspires.ftc.teamcode.aSClib;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class CommandScheduler {
    private HashMap<List<SubsystemBase>, CommandBase> commands = new HashMap<List<SubsystemBase>, CommandBase>();
    private List<CommandBase> defaultCommands = new ArrayList<CommandBase>();
    private List<SubsystemBase> currentRequirements = new ArrayList<SubsystemBase>();
    private List<SubsystemBase> subsystems = new ArrayList<SubsystemBase>();

    public CommandScheduler addSubsystem(SubsystemBase subsystem) {
        subsystems.add(subsystem);
        defaultCommands.addAll(subsystem.getDefaultCommand());
        return this;
    }

    public void addCommand(CommandBase command) {
        commands.put(command.getRequirements(), command);
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
        currentRequirements.clear();
        for (CommandBase i : commands.values()) {
            checkCommand(i);
            if (!checkRequirements(i.getRequirements())) {
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

    public void shutdown() {
        for (SubsystemBase i : subsystems) {
            i.shutdown();
        }
    }

    public void run() {
        runCommands();
        runDefaultCommands();
        runPeriodic();
    }
}