package org.firstinspires.ftc.teamcode.aSClib;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

public class ControllerAddons {

    private HashMap<Integer, Boolean> ButtonValues = new HashMap<Integer, Boolean>();
    private HashMap<Integer, Float> JoystickValues = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> TriggerValues = new HashMap<Integer, Float>();

    public void updateValues(Gamepad gamepad) {
        ButtonValues.put(0, gamepad.a);
        ButtonValues.put(1, gamepad.b);
        ButtonValues.put(2, gamepad.x);
        ButtonValues.put(3, gamepad.y);
        ButtonValues.put(4, gamepad.back);
        ButtonValues.put(5, gamepad.start);
        ButtonValues.put(6, gamepad.dpad_down);
        ButtonValues.put(7, gamepad.dpad_left);
        ButtonValues.put(8, gamepad.dpad_right);
        ButtonValues.put(9, gamepad.dpad_up);
        ButtonValues.put(10, gamepad.left_bumper);
        ButtonValues.put(11, gamepad.left_stick_button);
        ButtonValues.put(15, gamepad.right_bumper);
        ButtonValues.put(16, gamepad.right_stick_button);

        JoystickValues.put(12, gamepad.left_stick_x);
        JoystickValues.put(13, gamepad.left_stick_y);
        JoystickValues.put(17, gamepad.right_stick_x);
        JoystickValues.put(18, gamepad.right_stick_y);

        TriggerValues.put(14, gamepad.left_trigger);
        TriggerValues.put(19, gamepad.right_trigger);
    }

    public boolean getButton(Gamepad gamepad, int buttonKey) {
        updateValues(gamepad);
        for (int i : ButtonValues.keySet()) {
            if (i == buttonKey) {
                return ButtonValues.get(i);
            }
        }
        return false;
    }

    public float getJoystick(Gamepad gamepad, int joystickKey) {
        updateValues(gamepad);
        for (int i : JoystickValues.keySet()) {
            if (i == joystickKey) {
                return JoystickValues.get(i);
            }
        }
        return 0;
    }

    public float getTrigger(Gamepad gamepad, int triggerKey) {
        updateValues(gamepad);
        for (int i : TriggerValues.keySet()) {
            if (i == triggerKey) {
                return TriggerValues.get(i);
            }
        }
        return 0;
    }

}