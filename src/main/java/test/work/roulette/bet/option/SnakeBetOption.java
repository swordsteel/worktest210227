package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class SnakeBetOption implements BetOption {

    private static final String BET_NAME = "snake";
    private static final int MULTIPLIER = 3;
    private static final Set<Integer> NUMBERS = Set.of(1, 5, 9, 12, 14, 16, 19, 23, 27, 30, 32, 34);

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
