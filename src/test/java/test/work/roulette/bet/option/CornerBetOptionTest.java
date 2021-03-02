package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CornerBetOptionTest {

    @InjectMocks
    CornerBetOption betOption;

    @Test
    void wrongSize() {
        // given
        var betLess = BetRequest.builder().bet(Set.of(1,2,3)).build();
        var betMore = BetRequest.builder().bet(Set.of(1,2,3,4,5)).build();

        // then
        assertFalse(betOption.getBetOption(betLess).isPresent());
        assertFalse(betOption.getBetOption(betMore).isPresent());
    }

    @Test
    void corner() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,2,4,5)).build();

        // then
        assertTrue(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void cornerNotInSequentially() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,2,5,6)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void cornerLimit() {
        // given
        var horizontal = BetRequest.builder().bet(Set.of(3,4,6,7)).build();
        var vertical = BetRequest.builder().bet(Set.of(34,35,37,38)).build();

        // then
        assertFalse(betOption.getBetOption(horizontal).isPresent());
        assertFalse(betOption.getBetOption(vertical).isPresent());
    }

}