package org.firstinspires.ftc.teamcode;

public class PIDController {
    private double kP, kI, kD, kFF, kIL;
    private double output, integral, derivative;
    private double setPoint;
    private double processVariable;
    private double error;
    private double previousError;

    public PIDController(double kP, double kI, double kD, double kFF, double kIL) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kFF = kFF;
        this.kIL = kIL;
    }

    public void createSetPoint(double setPoint) {
        setPoint = this.setPoint;
        integral = 0;
        previousError = 0;
    }

    public void setProcessVariable(double processVariable) {
        processVariable = this.processVariable;
    }

    public double getError() {
        return setPoint - processVariable;
    }

    public double getOutput() {
        error = setPoint - processVariable;
        integral += error;
        derivative = error - previousError;
        output = kP * error
                + (integral > kIL ? 0 : kI * integral)
                + kD * derivative
                + kFF;
        return output;
    }
}
