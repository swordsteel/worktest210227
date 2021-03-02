package test.work.roulette.bet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import test.work.roulette.bet.option.BetOption;
import test.work.roulette.exception.InvalidBetException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BetServiceImplTest {

    @InjectMocks
    BetServiceImpl betService;

    @BeforeEach
    void setup() {
        var betOption = new BetOption() {

            @Override
            public String getName() {
                return "Test Bet";
            }

            @Override
            public int getMultiplier() {
                return 0;
            }

            @Override
            public Optional<Bet.BetBuilder> getBetOption(BetRequest request) {
                if(request.getBet() != null && request.getBet().size() == 1) {
                    return Optional.of(Bet.builder()
                            .number(request.getBet())
                            .play(this));
                }
                return Optional.empty();
            }

        };

        ReflectionTestUtils.setField(betService, "betOptions", Set.of(betOption));
    }

    @Test
    void invalidBetException() {
        // given
        var request = BetRequest.builder().build();

        // then
        assertThrows(InvalidBetException.class, () -> betService.getBetFromRequest(request));
    }

    @Test
    void validBet() {
        // given
        var request = BetRequest.builder().bet(Set.of(0)).build();

        // when
        var response = betService.getBetFromRequest(request);

        // then
        assertEquals(response.getPlay().getName(), "Test Bet");
    }

}