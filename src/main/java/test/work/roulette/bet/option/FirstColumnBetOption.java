package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class FirstColumnBetOption implements BetOption {

    private static final String BET_NAME = "first_column";
    private static final int MULTIPLIER = 3;
    private static final Set<Integer> NUMBERS = Set.of(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34);

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
