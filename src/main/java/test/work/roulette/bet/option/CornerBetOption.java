package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class CornerBetOption implements BetOption {

    private static final String BET_NAME = "corner";
    private static final int BET_SIZE = 4;
    private static final int MULTIPLIER = 9;

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
        if (request.getBet() == null || request.getBet().size() != BET_SIZE || !isCorner(request.getBet())) {
            return Optional.empty();
        }
        return Optional.of(Bet.builder()
                .number(Set.copyOf(request.getBet()))
                .play(this));
    }

    private boolean isCorner(Set<Integer> bet) {
        int start = bet.stream().sorted().findFirst().orElseThrow(NoSuchElementException::new);
        if (start > 33 || (start % 3) == 0) {
            return false;
        }
        return bet.contains(start + 1) && bet.contains(start + 3) && bet.contains(start + 4);
    }

}
