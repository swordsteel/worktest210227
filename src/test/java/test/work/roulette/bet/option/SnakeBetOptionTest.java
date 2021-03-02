package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SnakeBetOptionTest {

    @InjectMocks
    SnakeBetOption betOption;

    @Test
    void firstColumn() {
        // given
        var play = BetRequest.builder().play("Snake").build();

        // when
        var response = betOption.getBetOption(play);

        // then
        assertTrue(response.isPresent());
        assertTrue(response.get().build().getNumber().containsAll(Set.of(1, 5, 9, 12, 14, 16, 19, 23, 27, 30, 32, 34)));
    }

}