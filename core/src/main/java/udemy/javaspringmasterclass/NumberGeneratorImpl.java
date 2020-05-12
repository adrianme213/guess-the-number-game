package udemy.javaspringmasterclass;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {

    // ----- FIELDS -----
    private final Random random = new Random();
    private int maxNumber = 100;


    // ----- Public Methods ------
    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
