package test.work.roulette.bet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.work.roulette.wheel.WheelService;

import javax.validation.Valid;

@Validated
@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BetController {

    private final BetService betService;
    private final WheelService wheelService;

    @PostMapping("/bet")
    public ResponseEntity<?> makeBet(@Valid @RequestBody BetRequest request) {
        var bet = betService.getBetFromRequest(request);
        log.info("{} place a {} bet of value {} on the number {}",
                bet.getPlayerName(),
                bet.getPlay().getName(),
                bet.getAmount(),
                bet.getNumber());
        return ResponseEntity.ok(playRound(bet));
    }

    private BetResponse playRound(Bet bet) {
        var pocket = wheelService.spin();
        var response = BetResponse.builder().result(pocket);
        if (bet.getNumber().contains(pocket)) {
            var amount = Precision.round(bet.getAmount() * bet.getPlay().getMultiplier(), 2);
            response.win(true)
                    .multiplier(bet.getPlay().getMultiplier())
                    .amount(amount);
            log.info("Player wins {} on {} with a multiplier of {}", amount, pocket, bet.getPlay().getMultiplier());
        } else {
            log.info("House win on {}", pocket);
        }
        return response.build();
    }

}
