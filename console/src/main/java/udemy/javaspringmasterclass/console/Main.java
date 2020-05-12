package udemy.javaspringmasterclass.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import udemy.javaspringmasterclass.AppConfig;
import udemy.javaspringmasterclass.MessageGenerator;
import udemy.javaspringmasterclass.NumberGenerator;
import udemy.javaspringmasterclass.Game;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess the Number Game");

        // ----- CREATE CONTEXT ------ (container)
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        // ----- GET NUMBER GENERATOR BEAN FROM CONTEXT -----
        NumberGenerator numberGenerator
                = context.getBean(NumberGenerator.class);

        MessageGenerator messageGenerator
                = context.getBean(MessageGenerator.class);

        // ----- CALL METHOD -----
        int number = numberGenerator.next();
        log.info("Number = {}", number);

        log.info("getMainMessage = {}", messageGenerator.getMainMessage());
        log.info("getResultMessage = {}", messageGenerator.getResultMessage());

        // ----- GET GAME BEAN FROM CONTEXT -----
        Game game = context.getBean(Game.class);

        // ----- CLOSE CONTEXT (container) -----
        // to prevent memory resource leaks
        context.close();
    }
}
