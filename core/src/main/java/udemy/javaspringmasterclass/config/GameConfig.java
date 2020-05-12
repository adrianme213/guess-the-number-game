package udemy.javaspringmasterclass.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import udemy.javaspringmasterclass.GuessCount;
import udemy.javaspringmasterclass.MaxNumber;
import udemy.javaspringmasterclass.MinNumber;

@Configuration
@ComponentScan(basePackages = "udemy.javaspringmasterclass")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    // ----- FIELDS -----
    @Value("${game.maxNumber:50}")
    private int maxNumber;

    @Value("${game.minNumber:0}")
    private int minNumber;


    @Value("${game.guessCount:5}")
    private int guessCount;

    // ----- BEAN METHODS -----
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
}
