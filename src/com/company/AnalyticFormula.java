package com.company;

public final class AnalyticFormula {

    public static double black(double forward, double vol, double expiry, double strike,
                               OptionType callPut, double annuity) {
        final double logMoneyness = Math.log(forward / strike);
        final double stdDeviation = vol * Math.sqrt(expiry);

        final double d1 = logMoneyness/stdDeviation + 0.5 * stdDeviation;
        final double d2 = d1 - stdDeviation;

        final double flag = callPut == OptionType.Put ? -1. : 1.;
        final double nd1 = NormalDistribution.CDF(flag*d1);
        final double nd2 = NormalDistribution.CDF(flag*d2);
        return annuity * flag * (forward * nd1 - strike * nd2);
    }

    public static double blackScholes(double spot, double r, double vol, double expiry,
                                      double strike, OptionType callPut) {
        final double df = Math.exp(-r*expiry);
        final double forward = spot / df;
        return black(forward, vol, expiry, strike, callPut, df);
    }
}
