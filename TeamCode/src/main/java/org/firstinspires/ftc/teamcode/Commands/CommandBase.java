package org.firstinspires.ftc.teamcode.Commands;

public interface CommandBase {

    default CommandBase addRequirement() {
        return this;
    }

    default void initialize() {}

    default void execute() {}

    default void end(boolean interrupted) {}

    default boolean isFinished() {
        return false;
    }

    default void disabled() {}

    default void run() {
        initialize();
        do {
            execute();
        } while (!isFinished());
//        end(false);
    }
}
