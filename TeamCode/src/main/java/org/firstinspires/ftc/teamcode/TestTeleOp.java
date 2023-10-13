package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer;


@TeleOp(name="Official TeleOp")

public class TestTeleOp extends OpMode
{

    private RobotContainer robotContainer;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    public void init() {
        robotContainer = new RobotContainer(this, telemetry);
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public void loop() { robotContainer.run(); }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    public void stop() {
        robotContainer.shutdown();
    }

}