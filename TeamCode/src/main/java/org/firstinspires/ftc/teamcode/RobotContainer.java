package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumSubsystem;
import org.firstinspires.ftc.teamcode.PIDController;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.ControllerBinding;

import java.util.HashMap;

public class RobotContainer {
    private ControllerAddons controllerAddons;
    private Gamepad gamepad;

    private HardwareMap hwMap;
    private MecanumSubsystem mecanumSubsystem;

    public RobotContainer(Gamepad gamepad, HardwareMap hwMap) {
        this.gamepad = gamepad;
        this.hwMap = hwMap;
        controllerAddons = new ControllerAddons();
    }

    public void configureButtonBindings() {
        new ControllerBinding(gamepad, ControllerAddons.Controller.kABUTTON.getValue())
                .onTrue(new DriveCommand(gamepad, mecanumSubsystem));
    }
}
