package org.firstinspires.ftc.teamcode.aSClib;

import java.util.function.BooleanSupplier;

public class ConditionalCommand implements CommandBase{
    private CommandBase onTrue;
    private CommandBase onFalse;
    private BooleanSupplier condition;

    private CommandBase selectedCommand;

    public ConditionalCommand(CommandBase onTrue, CommandBase onFalse, BooleanSupplier condition) {
        if (onFalse == null) {

        }
        this.onTrue = onTrue;
        this.onFalse = onFalse;
        this.condition = condition;
    }

    @Override
    public void initialize() {
        if (condition.getAsBoolean()) {
            selectedCommand = onTrue;
        }
        else {
            selectedCommand = onFalse;
        }
        selectedCommand.initialize();
    }

    @Override
    public boolean isFinished() {
        return selectedCommand.isFinished();
    }

    @Override
    public void run() {
        selectedCommand.run();
    }
}
