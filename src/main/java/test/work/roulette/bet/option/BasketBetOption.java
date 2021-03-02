package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class BasketBetOption implements BetOption {

    private static final String BET_NAME = "basket";
    private static final int BET_SIZE = 4;
    private static final int MULTIPLIER = 9;
    private static final Set<Integer> NUMBERS = Set.of(0, 1, 2, 3);

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
        return isBet(request.getBet()).or(() -> isBetOption(request, BET_NAME, NUMBERS));
    }

    private Optional<Bet.BetBuilder> isBet(Set<Integer> bet) {
        if(bet != null && bet.size() == BET_SIZE && bet.containsAll(NUMBERS)) {

            return Optional.of(Bet.builder()
                    .number(Set.copyOf(NUMBERS))
                    .play(this));
        }
        return Optional.empty();
    }

}
