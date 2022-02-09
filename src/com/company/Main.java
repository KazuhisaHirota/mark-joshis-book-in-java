package com.company;

public class Main {

    public static void main(String[] args) {
        final double spot = 100.;
        final double r = 0.01;
        final double vol = 0.3;
        final double expiry = 1.;
        final double strike = 110.;
        final double  lowerLevel = 90.;
        final double  upperLevel = 130.;
        final int  numPath = 10000;

        final double bsCall = AnalyticFormula.blackScholes(spot, r, vol, expiry, strike, OptionType.Call);
        System.out.println("BS call price is " + bsCall);
        final double bsPut = AnalyticFormula.blackScholes(spot, r, vol, expiry, strike, OptionType.Put);
        System.out.println("BS put price is " + bsPut);

        TestMonteCarlo.run(expiry, strike, spot, vol, r, numPath);
        TestMonteCarlo.run2(expiry, strike, lowerLevel, upperLevel, spot, vol, r, numPath);
        TestMonteCarlo.run3(expiry, strike, lowerLevel, upperLevel, spot, vol, r, numPath);
    }
}
