package test.work.roulette.wheel;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class WheelServiceImpl implements WheelService {

    private static final int[] WHEEL = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30,
            8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public int spin() {
        return WHEEL[secureRandom.nextInt(WHEEL.length)];
    }

}
