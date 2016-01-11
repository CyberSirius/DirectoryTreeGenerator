package bg.rashev.traversal;

import java.util.Random;

/**
 * Created by CyberSirius on 10-Jan-16.
 */
class RandomGenerator {
    private final Random random = new Random();

    public RandomGenerator() {
    }

    public int generateRandomNumber(int lowerBound, int upperBound) {
        return lowerBound + random.nextInt(upperBound - lowerBound);
    }

    public int generateRandomNumber(int upperBound) {

        return random.nextInt(upperBound);
    }

    public boolean generateBoolean(int numerator, int denominator) {
        return generateRandomNumber(denominator) < numerator;
    }

    public String randomString(int len) {
        String alphabet = Constants.ALPHABET;
        StringBuilder stringBuilder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            stringBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return stringBuilder.toString();
    }
}