package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecondDozenBetOptionTest {

    @InjectMocks
    SecondDozenBetOption betOption;

    @Test
    void secondDozen() {
        // given
        var play = BetRequest.builder().play("second_dozen").build();

        // when
        var response = betOption.getBetOption(play);

        // then
        assertTrue(response.isPresent());
        assertTrue(response.get().build().getNumber().containsAll(Set.of(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)));
    }

}