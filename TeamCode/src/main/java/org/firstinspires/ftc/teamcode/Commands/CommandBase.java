package org.firstinspires.ftc.teamcode.Commands;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.firstinspires.ftc.teamcode.Subsystems.SubsystemBase;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface CommandBase {

    List<SubsystemBase> requirements = null;

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
