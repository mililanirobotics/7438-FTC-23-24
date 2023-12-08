package org.firstinspires.ftc.teamcode.aSClib;

import java.util.ArrayList;
import java.util.List;

public class SequentialCommandGroup implements CommandBase {
    List<CommandBase> commandList = new ArrayList<CommandBase>();

    public SequentialCommandGroup (CommandBase... command) {
        if (command.length == 0) {
            return;
        }

        for (CommandBase i : command) {
            commandList.add(i);
        }
    }

    public void runCommand(CommandBase commad) {
        if (commad.isFinished()) {
            commandList.remove(commad);
            return;
        }
        commad.run();
    }

    @Override
    public boolean isFinished() {
        return commandList.size() == 0;
    }

    @Override
    public void run() {
        runCommand(commandList.get(0));
    }
}
