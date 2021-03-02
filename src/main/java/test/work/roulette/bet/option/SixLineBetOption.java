package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

public class SixLineBetOption implements BetOption {

    private static final String BET_NAME = "six line";
    private static final int BET_SIZE = 6;
    private static final int MULTIPLIER = 6;

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
        if (request.getBet() == null || request.getBet().size() != BET_SIZE || !isSixLine(request.getBet())) {
            return Optional.empty();
        }
        return Optional.of(Bet.builder()
                .number(Set.copyOf(request.getBet()))
                .play(this));
    }

    private boolean isSixLine(Set<Integer> bet) {
        return IntStream.iterate(1, i -> i + 3)
                .limit(11)
                .anyMatch(value -> bet.containsAll(Set.of(value, value + 1, value + 2, value + 3, value + 4, value + 5)));
    }

}
