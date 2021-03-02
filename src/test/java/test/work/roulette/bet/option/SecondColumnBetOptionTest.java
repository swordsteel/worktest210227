package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SecondColumnBetOptionTest {

    @InjectMocks
    SecondColumnBetOption betOption;

    @Test
    void secondColumn() {
        // given
        var play = BetRequest.builder().play("Second_Column").build();

        // when
        var response = betOption.getBetOption(play);

        // then
        assertTrue(response.isPresent());
        assertTrue(response.get().build().getNumber().containsAll(Set.of(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35)));
    }

}
