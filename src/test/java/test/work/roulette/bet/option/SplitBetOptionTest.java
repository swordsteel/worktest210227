package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SplitBetOptionTest {

    @InjectMocks
    SplitBetOption betOption;

    @Test
    void wrongSize() {
        // given
        var betLess = BetRequest.builder().bet(Set.of(1)).build();
        var betMore = BetRequest.builder().bet(Set.of(1,2,3)).build();

        // then
        assertFalse(betOption.getBetOption(betLess).isPresent());
        assertFalse(betOption.getBetOption(betMore).isPresent());
    }

    @Test
    void horizontal() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,2)).build();

        // then
        assertTrue(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void horizontalNotInSequentially() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,3)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void horizontalLimit() {
        // given
        var bet = BetRequest.builder().bet(Set.of(3,4)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void vertical() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,4)).build();

        // then
        assertTrue(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void verticalNotInSequentially() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,7)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void verticalLimit() {
        // given
        var bet = BetRequest.builder().bet(Set.of(34,37)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void startWithZero() {
        // given
        var betZeroOne = BetRequest.builder().bet(Set.of(0,1)).build();
        var betZeroTwo = BetRequest.builder().bet(Set.of(0,2)).build();
        var betZeroThree = BetRequest.builder().bet(Set.of(0,3)).build();

        // then
        assertTrue(betOption.getBetOption(betZeroOne).isPresent());
        assertTrue(betOption.getBetOption(betZeroTwo).isPresent());
        assertTrue(betOption.getBetOption(betZeroThree).isPresent());
    }

}