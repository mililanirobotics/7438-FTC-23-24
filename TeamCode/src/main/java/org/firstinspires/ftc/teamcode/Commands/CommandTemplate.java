package org.firstinspires.ftc.teamcode.Commands;

public class CommandTemplate {

    public void initialize() {}

    public void execute() {}

    public boolean finished() {
        return true;
    }

    public void run() {
        initialize();
        do {
            execute();
        } while (finished());
    }
}
