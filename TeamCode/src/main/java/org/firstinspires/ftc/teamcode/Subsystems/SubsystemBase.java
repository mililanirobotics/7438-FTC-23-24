package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Commands.CommandBase;

public interface SubsystemBase {

    default void setDefaultCommand(CommandBase defaultCommand) {
    }

    default void periodic() {}
}
