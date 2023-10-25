package org.firstinspires.ftc.teamcode.aSClib;

import java.util.List;
import java.util.ArrayList;

public interface SubsystemBase {
    List<CommandBase> defaultCommands = new ArrayList<CommandBase>();

    default void setDefaultCommand(CommandBase defaultCommand) {
        defaultCommands.add(defaultCommand);
    }

    default List<CommandBase> getDefaultCommand() {
        return defaultCommands;
    }

    default void periodic() {}

    default void shutdown() {}

}