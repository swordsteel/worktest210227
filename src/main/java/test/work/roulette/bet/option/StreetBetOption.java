package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

public class StreetBetOption implements BetOption {

    private static final String BET_NAME = "street";
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
        if (request.getBet() == null || request.getBet().size() != BET_SIZE || !isStreet(request.getBet())) {
            return Optional.empty();
        }
        return Optional.of(Bet.builder()
                .number(Set.copyOf(request.getBet()))
                .play(this));
    }

    private boolean isStreet(Set<Integer> bet) {
        return IntStream.iterate(1, i -> i + 3)
                .limit(12)
                .anyMatch(value -> bet.containsAll(Set.of(value, value + 1, value + 2)));
    }

}
