package com.company;

public class PayoffCall extends StrikedTypePayoff {

    public PayoffCall(double strike) { super (strike); }

    public double Calc(double spot) { return Math.max(spot - strike(), 0.0); }
}