package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class BlackBetOption implements BetOption {

    private static final String BET_NAME = "black";
    private static final int MULTIPLIER = 2;
    private static final Set<Integer> NUMBERS = Set.of(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);

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
