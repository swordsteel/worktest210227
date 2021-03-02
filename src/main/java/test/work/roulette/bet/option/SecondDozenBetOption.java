package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class SecondDozenBetOption implements BetOption {

    private static final String BET_NAME = "second_dozen";
    private static final int MULTIPLIER = 3;
    private static final Set<Integer> NUMBERS = Set.of(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

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
        if (request.getPlay() != null && request.getPlay().equalsIgnoreCase(BET_NAME)) {
            return Optional.of(Bet.builder()
                    .number(Set.copyOf(NUMBERS))
                    .play(this));
        }
        return Optional.empty();
    }

}
