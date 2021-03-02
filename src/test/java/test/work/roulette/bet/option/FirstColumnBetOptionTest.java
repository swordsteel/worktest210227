package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FirstColumnBetOptionTest {

    @InjectMocks
    FirstColumnBetOption betOption;

    @Test
    void firstColumn() {
        // given
        var play = BetRequest.builder().play("First_Column").build();

        // when
        var response = betOption.getBetOption(play);

        // then
        assertTrue(response.isPresent());
        assertTrue(response.get().build().getNumber().containsAll(Set.of(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34)));
    }

}