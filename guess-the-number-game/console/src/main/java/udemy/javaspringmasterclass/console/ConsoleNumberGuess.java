package udemy.javaspringmasterclass.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import udemy.javaspringmasterclass.Game;
import udemy.javaspringmasterclass.MessageGenerator;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess {

    // ----- CONSTANTS -----
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    // ----- FIELDS -----
    private final Game game;

    private final MessageGenerator messageGenerator;

    // ----- CONSTRUCTORS -----
    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    // ----- EVENTS -----
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("Start --> Container ready for use.");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println((messageGenerator.getResultMessage()));

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();
            if(game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainString = scanner.nextLine().trim();
                if(!playAgainString.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }
    }
}
