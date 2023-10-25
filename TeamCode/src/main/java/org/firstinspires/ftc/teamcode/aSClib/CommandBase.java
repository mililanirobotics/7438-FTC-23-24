package org.firstinspires.ftc.teamcode.aSClib;

import java.util.ArrayList;
import java.util.List;

public interface CommandBase {
    List<SubsystemBase> requirements = new ArrayList<SubsystemBase>();

    default void initialize() {}

    default void execute() {}

    default void end(boolean interrupted) {}

    default boolean isFinished() {
        return false;
    }

    default void disabled() {}

    default void setRequirement(SubsystemBase subsystem) {
        requirements.add(subsystem);
    }

    default List<SubsystemBase> getRequirements() {
        return requirements;
    }

    default void run() {
        initialize();
        if (!isFinished()) {
            execute();
        }
//        end(false);
    }
}