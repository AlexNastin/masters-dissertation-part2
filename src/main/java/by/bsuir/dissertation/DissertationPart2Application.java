package by.bsuir.dissertation;

import by.bsuir.dissertation.configuration.MongoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MongoConfiguration.class})
public class DissertationPart2Application {

    public static void main(String[] args) {
        SpringApplication.run(DissertationPart2Application.class, args);
    }

    @Bean
    public CommandLineRunner run(CarTrafficDataTest carTrafficDataTest) {
        return args -> {
            carTrafficDataTest.run();
        };
    }
}