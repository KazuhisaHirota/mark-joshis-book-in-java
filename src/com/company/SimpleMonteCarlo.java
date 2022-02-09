package com.company;

import java.util.Random;

public class SimpleMonteCarlo {

    // function "SimpleMonteCarlo()"
    public static double run(double expiry, double strike, OptionType callPut,
                             double spot, double vol, double r, int numPath) {

        final double variance = vol * vol * expiry;
        final double rootVariance = Math.sqrt(variance);
        final double itoCorrection = -0.5 * variance;

        final double movedSpot = spot * Math.exp(r*expiry + itoCorrection);
        double runningSum = 0.;
        Random random = new Random(1234);

        for (int i=0; i<numPath; ++i) {
            final double thisGaussian = random.nextGaussian();
            final double thisSpot = movedSpot * Math.exp(rootVariance * thisGaussian);

            final double callPutFlag = callPut == OptionType.Put ? -1. : 1.;
            final double thisPayoff = Math.max(callPutFlag*(thisSpot - strike), 0.); // NOTE
            runningSum += thisPayoff;
        }

        final double mean = runningSum / (double) numPath;
        return mean * Math.exp(-r*expiry);
    }

    // function "SimpleMonteCarlo2()"
    public static double run2(Payoff payoff, double expiry,
                             double spot, double vol, double r, int numPath) {

        final double variance = vol * vol * expiry;
        final double rootVariance = Math.sqrt(variance);
        final double itoCorrection = -0.5 * variance;

        final double movedSpot = spot * Math.exp(r*expiry + itoCorrection);
        double runningSum = 0.;
        Random random = new Random(1234);

        for (int i=0; i<numPath; ++i) {
            final double thisGaussian = random.nextGaussian();
            final double thisSpot = movedSpot * Math.exp(rootVariance * thisGaussian);

            final double thisPayoff = payoff.Calc(thisSpot); // NOTE
            runningSum += thisPayoff;
        }

        final double mean = runningSum / (double) numPath;
        return mean * Math.exp(-r*expiry);
    }

    // function "SimpleMonteCarlo3()"
    public static double run3(VanillaOption theOption,
                              double spot, double vol, double r, int numPath) {

        final double expiry = theOption.expiry(); // NOTE
        final double variance = vol * vol * expiry;
        final double rootVariance = Math.sqrt(variance);
        final double itoCorrection = -0.5 * variance;

        final double movedSpot = spot * Math.exp(r*expiry + itoCorrection);
        double runningSum = 0.;
        Random random = new Random(1234);

        for (int i=0; i<numPath; ++i) {
            final double thisGaussian = random.nextGaussian();
            final double thisSpot = movedSpot * Math.exp(rootVariance * thisGaussian);

            final double thisPayoff = theOption.payoff(thisSpot); // NOTE
            runningSum += thisPayoff;
        }

        final double mean = runningSum / (double) numPath;
        return mean * Math.exp(-r*expiry);
    }
}
