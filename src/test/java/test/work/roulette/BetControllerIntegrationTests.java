package test.work.roulette;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import test.work.roulette.bet.BetController;
import test.work.roulette.bet.BetService;
import test.work.roulette.bet.BetServiceImpl;
import test.work.roulette.exception.InvalidBetException;
import test.work.roulette.wheel.WheelService;
import test.work.roulette.wheel.WheelServiceImpl;

import java.security.SecureRandom;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BetControllerIntegrationTests {

    private MockMvc mockMvc;

    BetController betController;
    BetService betService;
    WheelService wheelService;
    SecureRandom secureRandom;
    MockHttpServletRequestBuilder request;

    @BeforeEach
    public void setup() {
        betService = new BetServiceImpl();
        wheelService = new WheelServiceImpl();
        betController = new BetController(betService, wheelService);
        mockMvc = MockMvcBuilders.standaloneSetup(betController).build();

        secureRandom = mock(SecureRandom.class);
        ReflectionTestUtils.setField(wheelService, "secureRandom", secureRandom);

        request = MockMvcRequestBuilders.post("/api/bet").contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void emptyRequest() throws Exception {
        // when
        var response = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/bet")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));

        // then
        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void playBetNegative() throws Exception {
        // when
        var response = mockMvc.perform(request
                .content("{\"bet\":[-1]}"));

        // then
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException())
                        .getMessage().contains("bet must be in range 0 to 36")));
    }

    @Test
    public void playBetAboveThirtySix() throws Exception {
        // when
        var response = mockMvc.perform(request.content("{\"bet\":[37]}"));

        // then
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException())
                        .getMessage().contains("bet must be in range 0 to 36")));
    }

    @Test
    public void playInvalidBetOption() throws Exception {
        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[0,36]}"));

        // then
        response.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidBetException))
                .andExpect(result -> assertEquals(
                        "dont match any bet option",
                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void playSingleBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(0);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[0]}"));

        // then
        isWinner(response, 0, 36, 3.6);
    }

    @Test
    public void playSplitVerticalBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(0);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[0,1]}"));

        // then
        isWinner(response, 0, 18, 1.8);
    }

    @Test
    public void playSplitBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(23);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[1,2]}"));

        // then
        isWinner(response, 1, 18, 1.8);
    }

    @Test
    public void playStreetBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(23);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[1,2,3]}"));

        // then
        isWinner(response, 1, 12, 1.2);
    }

    @Test
    public void playTrioBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(0);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[0,2,3]}"));

        // then
        isWinner(response, 0, 12, 1.2);
    }

    @Test
    public void playCornerBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(19);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[1,2,4,5]}"));

        // then
        isWinner(response, 5, 9, 0.9);
    }

    @Test
    public void playBasketBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(0);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[0,1,2,3]}"));

        // then
        isWinner(response, 0, 9, 0.9);
    }

    @Test
    public void playBasketPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(0);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"basket\"}"));

        // then
        isWinner(response, 0, 9, 0.9);
    }

    @Test
    public void playSixLineBetOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(22);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"bet\":[31,32,33,34,35,36]}"));

        // then
        isWinner(response, 33, 6, 0.6);
    }

    @Test
    public void playFirstColumnPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(23);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"first_column\"}"));

        // then
        isWinner(response, 1, 3, 0.3);
    }

    @Test
    public void playSecondColumnPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(6);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"second_column\"}"));

        // then
        isWinner(response, 2, 3, 0.3);
    }

    @Test
    public void playThirdColumnPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(35);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"third_column\"}"));

        // then
        isWinner(response, 3, 3, 0.3);
    }

    @Test
    public void playFirstDozenPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(23);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"first_dozen\"}"));

        // then
        isWinner(response, 1, 3, 0.3);
    }

    @Test
    public void playSecondDozenPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(12);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"second_dozen\"}"));

        // then
        isWinner(response, 13, 3, 0.3);
    }

    @Test
    public void playThirdDozenPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(7);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"third_dozen\"}"));

        // then
        isWinner(response, 25, 3, 0.3);
    }

    @Test
    public void playSnakePlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(21);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"snake\"}"));

        // then
        isWinner(response, 16, 3, 0.3);
    }

    @Test
    public void playOddPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(17);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"odd\"}"));

        // then
        isWinner(response, 23, 2, 0.2);
    }

    @Test
    public void playEvenPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(20);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"even\"}"));

        // then
        isWinner(response, 24, 2, 0.2);
    }

    @Test
    public void playBlackPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(14);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"black\"}"));

        // then
        isWinner(response, 11, 2, 0.2);
    }

    @Test
    public void playRedPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(25);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"red\"}"));

        // then
        isWinner(response, 14, 2, 0.2);
    }

    @Test
    public void playLowPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(29);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"low\"}"));

        // then
        isWinner(response, 18, 2, 0.2);
    }

    @Test
    public void playHighPlayOption() throws Exception {
        // given
        when(secureRandom.nextInt(37)).thenReturn(3);

        // when
        var response = mockMvc.perform(request
                .content("{\"player_name\":\"test\",\"amount\":0.1,\"play\":\"high\"}"));

        // then
        isWinner(response, 19, 2, 0.2);
    }

    private void isWinner(ResultActions response, int result, int multiplier, double amount) throws Exception {
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(result))
                .andExpect(MockMvcResultMatchers.jsonPath("$.win").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.multiplier").value(multiplier))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(amount));
    }

}
