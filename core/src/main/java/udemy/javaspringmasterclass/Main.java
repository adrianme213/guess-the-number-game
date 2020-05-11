package udemy.javaspringmasterclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess the Number Game");

        // ----- CREATE CONTEXT ------ (container)
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        // ----- GET NUMBER GENERATOR BEAN FROM CONTEXT -----
        NumberGenerator numberGenerator
                = context.getBean(NumberGenerator.class);

        // ----- CALL METHOD -----
        int number = numberGenerator.next();
        log.info("Number = {}", number);

        // ----- GET GAME BEAN FROM CONTEXT -----
        Game game = context.getBean(Game.class);

        // ----- CLOSE CONTEXT (container) -----
        // to prevent memory resource leaks
        context.close();
    }
}
