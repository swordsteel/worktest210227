package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class TrioBetOption implements BetOption {

    private static final String BET_NAME = "trio";
    private static final int BET_SIZE = 3;
    private static final int MULTIPLIER = 12;

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
        if (request.getBet() == null || request.getBet().size() != BET_SIZE || !isTrio(request.getBet())) {
            return Optional.empty();
        }
        return Optional.of(Bet.builder()
                .number(Set.copyOf(request.getBet()))
                .play(this));
    }

    private boolean isTrio(Set<Integer> bet) {
        return bet.containsAll(Set.of(0, 1, 2)) || bet.containsAll(Set.of(0, 2, 3));
    }

}
