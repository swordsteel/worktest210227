package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class SecondColumnBetOption implements BetOption {

    private static final String BET_NAME = "second_column";
    private static final int MULTIPLIER = 3;
    private static final Set<Integer> NUMBERS = Set.of(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35);

    @Override
    public String getName() {
        return BET_NAME;
    }

    @Override
    public int getMultiplier() {
        return MULTIPLIER;
    }

    @Override
    public Optional<Bet.BetBuilder> getBetOption(BetRequest request) {
        return isBetOption(request, BET_NAME, NUMBERS);
    }

}
