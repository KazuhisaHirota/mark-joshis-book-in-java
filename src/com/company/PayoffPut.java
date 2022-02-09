package com.company;

public class PayoffPut extends StrikedTypePayoff {

    public PayoffPut(double strike) {
        super (strike);
    }

    public double Calc(double spot) {
        return Math.max(strike() - spot, 0.0);
    }
}
