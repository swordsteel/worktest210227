package test.work.roulette.bet;

import lombok.Builder;
import lombok.Getter;
import test.work.roulette.bet.option.BetOption;

import java.util.Set;

@Getter
@Builder
public class Bet {

    private final String playerName;
    private final BetOption play;
    private final Set<Integer> number;
    private final double amount;

}
