package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Commands.CommandBase;
import java.util.List;

public interface SubsystemBase {
    List<CommandBase> defaultCommands = null;

    default void setDefaultCommand(CommandBase defaultCommand) {
        defaultCommands.add(defaultCommand);
    }

    default List<CommandBase> getDefaultCommand() {
        return defaultCommands;
    }

    default void periodic() {}

    default void shutdown() {}

}
