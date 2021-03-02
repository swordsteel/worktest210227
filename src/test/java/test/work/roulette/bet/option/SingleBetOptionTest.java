package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SingleBetOptionTest {

    @InjectMocks
    SingleBetOption betOption;

    @Test
    void wrongSize() {
        // given
        var betLess = BetRequest.builder().bet(Set.of()).build();
        var betMore = BetRequest.builder().bet(Set.of(1,2)).build();

        // then
        assertFalse(betOption.getBetOption(betLess).isPresent());
        assertFalse(betOption.getBetOption(betMore).isPresent());

    }

    @Test
    void single() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1)).build();

        // then
        assertTrue(betOption.getBetOption(bet).isPresent());
    }

}
