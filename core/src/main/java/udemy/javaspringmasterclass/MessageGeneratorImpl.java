package udemy.javaspringmasterclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MessageGeneratorImpl implements MessageGenerator {

    // ----- CONSTANTS -----
    private static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // ----- FIELDS -----
    @Autowired
    private Game game;
    private int guessCount = 10;

    // ----- INIT -----
    @PostConstruct
    public void init() {
        log.info("Game = {}", game);
    }

    // ----- PUBLIC METHODS -----
    @Override
    public String getMainMessage() {
        return "Number is between " + game.getSmallest() + " and "
                + game.getBiggest() + ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        String resultMessage;
        if(game.isGameWon()) {
            resultMessage = "You guessed it! The number was " + game.getNumber();
        } else if(game.isGameLost()) {
            resultMessage = "You lost. The number was " + game.getNumber();
        } else if (!game.isValidNumberRange()) {
            resultMessage = "Invalid number range!";
        } else if(game.getRemainingGuesses() == guessCount) {
            resultMessage = "What is your first guess?";
        } else {
            String direction = "Lower";
            if(game.getGuess() < game.getNumber()) {
                direction = "Higher";
            }
            resultMessage = direction + "! You have " + game.getRemainingGuesses() + " guess left.";
        }
        return resultMessage;
    }
}
