package org.firstinspires.ftc.teamcode;

public class PIDController {
    private double kP, kI, kD, kFF, kIL;
    private double output, integral, derivative;
    private double setPoint;
    private double processVariable;
    private double error;
    private double previousError;

    public PIDController setProportional(double kP) {
        this.kP = kP;
        return this;
    }

    public PIDController setIntegral(double kI) {
        this.kI = kI;
        return this;
    }

    public PIDController setDerivative(double kD) {
        this.kD = kD;
        return this;
    }

    public PIDController setFeedForward(double kFF) {
        this.kFF = kFF;
        return this;
    }

    public PIDController setIntegralLimiter(double kIL) {
        this.kIL = kIL;
        return this;
    }

    public void createSetPoint(double setPoint) {
        setPoint = this.setPoint;
        integral = 0;
        previousError = 0;
    }

    public void setProcessVariable(double processVariable) {
        processVariable = this.processVariable;
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
