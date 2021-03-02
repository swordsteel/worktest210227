package test.work.roulette.bet;

import org.springframework.stereotype.Service;
import test.work.roulette.bet.option.*;
import test.work.roulette.exception.InvalidBetException;
import org.apache.commons.math3.util.Precision;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class BetServiceImpl implements BetService {

    private final Set<BetOption> betOptions = new HashSet<>();

    public BetServiceImpl() {
        betOptions.add(new SingleBetOption());
        betOptions.add(new SplitBetOption());
        betOptions.add(new StreetBetOption());
        betOptions.add(new TrioBetOption());
        betOptions.add(new CornerBetOption());
        betOptions.add(new BasketBetOption());
        betOptions.add(new SixLineBetOption());
        betOptions.add(new FirstColumnBetOption());
        betOptions.add(new SecondColumnBetOption());
        betOptions.add(new ThirdColumnBetOption());
        betOptions.add(new FirstDozenBetOption());
        betOptions.add(new SecondDozenBetOption());
        betOptions.add(new ThirdDozenBetOption());
        betOptions.add(new SnakeBetOption());
        betOptions.add(new OddBetOption());
        betOptions.add(new EvenBetOption());
        betOptions.add(new BlackBetOption());
        betOptions.add(new RedBetOption());
        betOptions.add(new LowBetOption());
        betOptions.add(new HighBetOption());
    }

    @Override
    public Bet getBetFromRequest(BetRequest request) {
        return betOptions.stream()
                .map(option -> option.getBetOption(request))
                .filter(Optional::isPresent)
                .map(builder -> builder.get()
                        .playerName(request.getPlayerName())
                        .amount(Precision.round(request.getAmount(), 2))
                        .build())
                .findFirst()
                .orElseThrow(() -> new InvalidBetException("dont match any bet option"));
    }

}
