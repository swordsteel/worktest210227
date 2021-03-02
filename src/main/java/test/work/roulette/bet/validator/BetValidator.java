package test.work.roulette.bet.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import test.work.roulette.bet.BetRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BetValidator implements ConstraintValidator<BetConstraint, BetRequest> {

    @Override
    public void initialize(BetConstraint constraint) {
    }

    @Override
    public boolean isValid(BetRequest request, ConstraintValidatorContext context) {
        boolean valid = false;
        if (CollectionUtils.isEmpty(request.getBet()) && StringUtils.isBlank(request.getPlay())) {
            context.buildConstraintViolationWithTemplate("bet cant be empty").addConstraintViolation();
        } else {
            valid = isValidBets(request, context);
        }
        return valid;
    }

    private boolean isValidBets(BetRequest request, ConstraintValidatorContext context) {
        if (request.getBet() == null || request.getBet().stream().noneMatch(integer -> integer < 0 || integer > 36)) {
            return true;
        }
        context.buildConstraintViolationWithTemplate("bet must be in range 0 to 36").addConstraintViolation();
        return false;
    }

}
