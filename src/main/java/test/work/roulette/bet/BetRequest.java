package test.work.roulette.bet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.work.roulette.bet.validator.BetConstraint;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Builder
@BetConstraint
@NoArgsConstructor
@AllArgsConstructor
public class BetRequest {

    @NotBlank
    @JsonProperty("player_name")
    private String playerName;
    private String play;
    private Set<Integer> bet;
    @DecimalMin("0.1")
    private double amount;

}
