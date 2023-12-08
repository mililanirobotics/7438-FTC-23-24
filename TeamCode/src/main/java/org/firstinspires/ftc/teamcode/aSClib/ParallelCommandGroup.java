package org.firstinspires.ftc.teamcode.aSClib;

import org.firstinspires.ftc.teamcode.aSClib.CommandBase;

import java.util.List;
import java.util.ArrayList;

public class ParallelCommandGroup implements CommandBase {
    List<CommandBase> commandList = new ArrayList<CommandBase>();

    public ParallelCommandGroup insertCommand(CommandBase command) {
        commandList.add(command);
        return this;
    }

    public void runCommand(CommandBase commad) {
        if (commad.isFinished()) {
            commandList.remove(commad);
            return;
        }
        commad.run();
    }

    @Override
    public void initialize() {
        for (CommandBase i : commandList) {
            i.initialize();
        }
    }

    @Override
    public boolean isFinished() {
        return commandList.size() == 0;
    }

    @Override
    public void run() {
        for (CommandBase i : commandList) {
            runCommand(i);
        }
    }
}
