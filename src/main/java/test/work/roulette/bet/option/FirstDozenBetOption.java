package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class FirstDozenBetOption implements BetOption {

    private static final String BET_NAME = "first_dozen";
    private static final int MULTIPLIER = 3;
    private static final Set<Integer> NUMBERS = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

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
