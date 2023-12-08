package org.firstinspires.ftc.teamcode.aSClib;

import java.util.List;
import java.util.ArrayList;

public interface SubsystemBase {
    default void setDefaultCommand(CommandScheduler scheduler, CommandBase defaultCommand) {
        scheduler.setDefaultCommand(defaultCommand);
    }

    default void periodic() {}

    default void shutdown() {}

}