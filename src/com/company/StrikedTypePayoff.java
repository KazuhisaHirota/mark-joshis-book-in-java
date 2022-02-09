package com.company;


public abstract class StrikedTypePayoff implements Payoff {

    private final double strike;

    protected StrikedTypePayoff(double strike) { this.strike = strike; }

    public final double strike() { return strike; }
}

