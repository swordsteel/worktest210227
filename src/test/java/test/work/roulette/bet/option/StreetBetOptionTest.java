package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StreetBetOptionTest {

    @InjectMocks
    StreetBetOption betOption;

    @Test
    void wrongSize() {
        // given
        var betLess = BetRequest.builder().bet(Set.of(1,2)).build();
        var betMore = BetRequest.builder().bet(Set.of(1,2,3,4)).build();

        // then
        assertFalse(betOption.getBetOption(betLess).isPresent());
        assertFalse(betOption.getBetOption(betMore).isPresent());
    }

    @Test
    void street() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,2,3)).build();

        // then
        assertTrue(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void streetNotInSequentially() {
        // given
        var bet = BetRequest.builder().bet(Set.of(1,3,5)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

    @Test
    void streetLimit() {
        // given
        var bet = BetRequest.builder().bet(Set.of(37,38,39)).build();

        // then
        assertFalse(betOption.getBetOption(bet).isPresent());
    }

}