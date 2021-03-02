package test.work.roulette.bet;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BetResponse {

    private final int result;
    private final boolean win;
    private final Integer multiplier;
    private final Double amount;

}
