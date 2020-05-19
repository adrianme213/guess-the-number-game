package udemy.javaspringmasterclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class GameImpl implements Game {

    // ----- CONSTANTS -----
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    // ----- FIELDS -----
    private final NumberGenerator numberGenerator;

    private final int guessCount;

    private int smallest;
    private int number;
    private int guess;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    // ----- INIT METHODS -----
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount, @MinNumber int smallest) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
        this.smallest = smallest;
    }

    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber ();
        number = numberGenerator.next();
        log.debug("The number is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("In Game preDestroy()");
    }

    // ----- PUBLIC METHODS -----
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void check() {
        checkValidNumberRange();

        if(validNumberRange) {
            if(guess > number) {
                biggest = guess - 1;
            }
            if(guess < number) {
                smallest = guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // ----- PRIVATE METHODS -----
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
