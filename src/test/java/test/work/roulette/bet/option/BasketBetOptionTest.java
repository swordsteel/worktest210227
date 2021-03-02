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
class BasketBetOptionTest {

    @InjectMocks
    BasketBetOption betOption;

    @Test
    void wrongSize() {
        // given
        var betLess = BetRequest.builder().bet(Set.of(1, 2, 3)).build();
        var betMore = BetRequest.builder().bet(Set.of(1, 2, 3, 4, 5)).build();

        // then
        assertFalse(betOption.getBetOption(betLess).isPresent());
        assertFalse(betOption.getBetOption(betMore).isPresent());
    }

    @Test
    void basket() {
        // given
        var bet = BetRequest.builder().bet(Set.of(0, 1, 2, 3)).build();
        var play = BetRequest.builder().play("basket").build();

        // then
        assertTrue(betOption.getBetOption(bet).isPresent());
        assertTrue(betOption.getBetOption(play).isPresent());
    }

    @Test
    void badBasket() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1, 2, 3, 4)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

}