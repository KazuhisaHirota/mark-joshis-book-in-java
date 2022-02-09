package com.company;

public final class TestMonteCarlo {

    public static void run(double expiry, double strike, double spot, double vol, double r, int numPath) {
        final double callPrice = SimpleMonteCarlo.run(expiry, strike, OptionType.Call, spot, vol, r, numPath);
        System.out.println("MC call price is " + callPrice);

        final double putPrice = SimpleMonteCarlo.run(expiry, strike, OptionType.Put, spot, vol, r, numPath);
        System.out.println("MC put price is " + putPrice);
    }

    public static void run2(double expiry, double strike, double lowerLevel, double upperLevel,
                            double spot, double vol, double r, int numPath) {
        final Payoff callPayoff = new PayoffCall(strike);
        final double callPrice = SimpleMonteCarlo.run2(callPayoff, expiry, spot, vol, r, numPath);
        System.out.println("MC call price is " + callPrice);

        final Payoff putPayoff = new PayoffPut(strike);
        final double putPrice = SimpleMonteCarlo.run2(putPayoff, expiry, spot, vol, r, numPath);
        System.out.println("MC put price is " + putPrice);

        final Payoff digitalPayoff = new PayoffDoubleDigital(lowerLevel, upperLevel);
        final double digitalPrice = SimpleMonteCarlo.run2(digitalPayoff, expiry, spot, vol, r, numPath);
        System.out.println("MC digital price is " + digitalPrice);
    }

    public static void run3(double expiry, double strike, double lowerLevel, double upperLevel,
                            double spot, double vol, double r, int numPath) {
        final var callOption = new VanillaOption(expiry, new PayoffCall(strike));
        final double callPrice = SimpleMonteCarlo.run3(callOption, spot, vol, r, numPath);
        System.out.println("MC call price is " + callPrice);

        final var putOption = new VanillaOption(expiry, new PayoffPut(strike));
        final double putPrice = SimpleMonteCarlo.run3(putOption, spot, vol, r, numPath);
        System.out.println("MC put price is " + putPrice);

        final var digitalOption = new VanillaOption(expiry, new PayoffDoubleDigital(lowerLevel, upperLevel));
        final double digitalPrice = SimpleMonteCarlo.run3(digitalOption, spot, vol, r, numPath);
        System.out.println("MC digital price is " + digitalPrice);
    }
}
