package com.company;

public class PayoffDoubleDigital implements Payoff {

    private final double lowerLevel, upperLevel;

    public PayoffDoubleDigital(double lowerLevel, double upperLevel) {
        this.lowerLevel = lowerLevel;
        this.upperLevel = upperLevel;
    }

    public double Calc(double spot) {
        if (spot <= lowerLevel) {
            return 0.;
        } else if (spot >= upperLevel) {
            return 0.;
        } else {
            return 1.;
        }
    }
}