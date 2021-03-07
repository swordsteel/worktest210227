package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

public class SplitBetOption implements BetOption {

    private static final String BET_NAME = "split";
    private static final int BET_SIZE = 2;
    private static final int MULTIPLIER = 18;

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
        if (request.getBet() == null || request.getBet().size() != BET_SIZE || !isSplit(request.getBet())) {
            return Optional.empty();
        }
        return Optional.of(Bet.builder()
                .number(Set.copyOf(request.getBet()))
                .play(this));
    }

    private boolean isSplit(Set<Integer> bet) {
        var start = bet.stream().sorted().findFirst().orElseThrow(NoSuchElementException::new);
        return isHorizontal(start, bet) || isVertical(start, bet);
    }

    private boolean isVertical(Integer start, Set<Integer> bet) {
        if (start > 33) {
            return false;
        }
        return bet.contains(start + 3) || (start == 0 && (bet.contains(1) || bet.contains(2)));
    }

    private boolean isHorizontal(Integer start, Set<Integer> bet) {
        if ((start % 3) == 0) {
            return false;
        }
        return bet.contains(start + 1);
    }

}
