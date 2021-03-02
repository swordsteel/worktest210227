package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class ThirdColumnBetOption implements BetOption {

    private static final String BET_NAME = "third_column";
    private static final int MULTIPLIER = 3;
    private static final Set<Integer> NUMBERS = Set.of(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36);

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
