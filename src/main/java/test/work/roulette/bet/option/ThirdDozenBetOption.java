package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class ThirdDozenBetOption implements BetOption {

    private static final String BET_NAME = "third_dozen";
    private static final int MULTIPLIER = 3;
    private static final Set<Integer> NUMBERS = Set.of(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);

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
