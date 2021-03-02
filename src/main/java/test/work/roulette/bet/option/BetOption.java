package test.work.roulette.bet.option;

import test.work.roulette.bet.Bet;
import test.work.roulette.bet.BetRequest;

import java.util.Optional;
import java.util.Set;

public interface BetOption {

    String getName();

    int getMultiplier();

    Optional<Bet.BetBuilder> getBetOption(BetRequest request);

    default Optional<Bet.BetBuilder> isBetOption(BetRequest request, String betName, Set<Integer> numbers) {
        if (request.getPlay() != null && request.getPlay().equalsIgnoreCase(betName)) {
            return Optional.of(Bet.builder()
                    .number(Set.copyOf(numbers))
                    .play(this));
        }
        return Optional.empty();
    }

}
