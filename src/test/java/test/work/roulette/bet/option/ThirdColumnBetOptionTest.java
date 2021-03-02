package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ThirdColumnBetOptionTest {

    @InjectMocks
    ThirdColumnBetOption betOption;

    @Test
    void thirdColumn() {
        // given
        var play = BetRequest.builder().play("Third_Column").build();

        // when
        var response = betOption.getBetOption(play);

        // then
        assertTrue(response.isPresent());
        assertTrue(response.get().build().getNumber().containsAll(Set.of(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36)));
    }

}