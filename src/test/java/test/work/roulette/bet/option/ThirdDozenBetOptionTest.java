package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ThirdDozenBetOptionTest {

    @InjectMocks
    ThirdDozenBetOption betOption;

    @Test
    void thirdDozen() {
        // given
        var play = BetRequest.builder().play("third_dozen").build();

        // when
        var response = betOption.getBetOption(play);

        // then
        assertTrue(response.isPresent());
        assertTrue(response.get().build().getNumber().containsAll(Set.of(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36)));
    }

}