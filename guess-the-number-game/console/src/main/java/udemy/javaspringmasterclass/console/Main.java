package udemy.javaspringmasterclass.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import udemy.javaspringmasterclass.config.GameConfig;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess the Number Game");

        // ----- CREATE CONTEXT ------ (container)
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(GameConfig.class);

        // ----- CLOSE CONTEXT (container) -----
        // to prevent memory resource leaks
        context.close();
    }
}
