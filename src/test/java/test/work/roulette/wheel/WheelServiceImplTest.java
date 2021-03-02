package test.work.roulette.wheel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.SecureRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WheelServiceImplTest {

    @InjectMocks
    WheelServiceImpl wheelService;

    @Mock
    SecureRandom secureRandom;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(wheelService, "secureRandom", secureRandom);
    }

    @Test
    public void singleZeroWheel() {
        // given
        testPocketResponse(0, 0);
        testPocketResponse(1, 32);
        testPocketResponse(2, 15);
        testPocketResponse(3, 19);
        testPocketResponse(4, 4);
        testPocketResponse(5, 21);
        testPocketResponse(6, 2);
        testPocketResponse(7, 25);
        testPocketResponse(8, 17);
        testPocketResponse(9, 34);
        testPocketResponse(10, 6);
        testPocketResponse(11, 27);
        testPocketResponse(12, 13);
        testPocketResponse(13, 36);
        testPocketResponse(14, 11);
        testPocketResponse(15, 30);
        testPocketResponse(16, 8);
        testPocketResponse(17, 23);
        testPocketResponse(18, 10);
        testPocketResponse(19, 5);
        testPocketResponse(20, 24);
        testPocketResponse(21, 16);
        testPocketResponse(22, 33);
        testPocketResponse(23, 1);
        testPocketResponse(24, 20);
        testPocketResponse(25, 14);
        testPocketResponse(26, 31);
        testPocketResponse(27, 9);
        testPocketResponse(28, 22);
        testPocketResponse(29, 18);
        testPocketResponse(30, 29);
        testPocketResponse(31, 7);
        testPocketResponse(32, 28);
        testPocketResponse(33, 12);
        testPocketResponse(34, 35);
        testPocketResponse(35, 3);
        testPocketResponse(36, 26);
    }

    private void testPocketResponse(int pocket, int response) {
        // given
        when(secureRandom.nextInt(37)).thenReturn(pocket);

        // then
        assertThat(wheelService.spin()).isEqualTo(response);
    }

}
