package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public class SingleBetOption implements BetOption {

    private static final String BET_NAME = "single";
    private static final int BET_SIZE = 1;
    private static final int MULTIPLIER = 36;

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
        if (request.getBet() == null || request.getBet().size() != BET_SIZE) {
            return Optional.empty();
        }
        return Optional.of(Bet.builder()
                .number(Set.copyOf(request.getBet()))
                .play(this));
    }

}
