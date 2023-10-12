package org.firstinspires.ftc.teamcode.Commands;

public interface CommandBase {

    default void initialize() {}

    default void execute() {}

    default void end(boolean interrupted) {}

    default boolean isFinished() {
        return false;
    }

    default void disabled() {}

    default void run() {
        initialize();
        if (!isFinished()) {
            execute();
        }
//        end(false);
    }
}
