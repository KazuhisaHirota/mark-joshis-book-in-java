package com.company;

public class VanillaOption {

    private final double expiry;
    private final Payoff payoff;

    public VanillaOption(double expiry, Payoff payoff) {
        this.expiry = expiry;
        this.payoff = payoff;
    }

    public double payoff(double spot) { return payoff.Calc(spot); }

    public double expiry() { return expiry; }
}
