package test.work.roulette.bet.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import test.work.roulette.bet.BetRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FirstDozenBetOptionTest {

    @InjectMocks
    FirstDozenBetOption betOption;

    @Test
    void firstDozen() {
        // given
        var play = BetRequest.builder().play("First_Dozen").build();

        // when
        var response = betOption.getBetOption(play);

        // then
        assertTrue(response.isPresent());
        assertTrue(response.get().build().getNumber().containsAll(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)));
    }

}