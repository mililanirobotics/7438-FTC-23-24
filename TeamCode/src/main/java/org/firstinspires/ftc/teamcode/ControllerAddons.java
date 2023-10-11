package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

public class ControllerAddons {
    public enum Controller {
        kABUTTON(0),
        kBBUTTON(1),
        kXBUTTON(2),
        kYBUTTON(3),
        kBACK(4),
        kSTART(5),
        kDPADDOWN(6),
        kDPADLEFT(7),
        kDPADRIGHT(8),
        kDPADUP(9),
        kLEFTBUMPER(10),
        kLEFTSTICKBUTTON(11),
        kLEFTTRIGGER(12),
        kRIGHTBUMPER(13),
        kRIGHTSTICKBUTTON(14),
        kRIGHTTRIGGER(15);

        private final int value;

        Controller(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }

    private HashMap<Integer, Boolean> ControllerValues = new HashMap<Integer, Boolean>();

    public void updateValues(Gamepad gamepad) {
        ControllerValues.put(0, gamepad.a);
        ControllerValues.put(1, gamepad.b);
        ControllerValues.put(2, gamepad.x);
        ControllerValues.put(3, gamepad.y);
        ControllerValues.put(4, gamepad.back);
        ControllerValues.put(5, gamepad.start);
        ControllerValues.put(6, gamepad.dpad_down);
        ControllerValues.put(7, gamepad.dpad_left);
        ControllerValues.put(8, gamepad.dpad_right);
        ControllerValues.put(9, gamepad.dpad_up);
        ControllerValues.put(10, gamepad.left_bumper);
        ControllerValues.put(11, gamepad.left_stick_button);
        ControllerValues.put(12, gamepad.left_trigger > 0.5 ? true : false);
        ControllerValues.put(13, gamepad.right_bumper);
        ControllerValues.put(14, gamepad.right_stick_button);
        ControllerValues.put(15, gamepad.right_trigger > 0.5 ? true : false);
    }

    public boolean get(Gamepad gamepad, int buttonKey) {
        updateValues(gamepad);
        for (int i : ControllerValues.keySet()) {
            if (i == buttonKey) {
                return ControllerValues.get(i);
            }
        }
        return false;
    }

}
