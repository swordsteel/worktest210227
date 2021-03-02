package test.work.roulette.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBetException extends RuntimeException {

    public InvalidBetException(String message) {
        super(message);
    }

}
