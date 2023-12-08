package org.firstinspires.ftc.teamcode.aSClib;

import java.util.ArrayList;
import java.util.List;

public interface CommandBase {
    List<SubsystemBase> requirements = new ArrayList<SubsystemBase>();

    default void initialize() {}

    default void execute() {}

    default void end() {}

    default boolean isFinished() {
        return false;
    }

    default void disabled() {}

    default void addRequirement(SubsystemBase subsystem) {
        requirements.add(subsystem);
    }

    default List<SubsystemBase> getRequirements() {
        return requirements;
    }

    default void run() {
        if (!isFinished()) {
            execute();
        }
        if (isFinished()) {
            end();
        }
    }
}