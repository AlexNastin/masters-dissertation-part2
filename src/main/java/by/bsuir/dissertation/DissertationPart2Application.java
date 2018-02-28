package by.bsuir.dissertation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DissertationPart2Application {

    public static void main(String[] args) {
        SpringApplication.run(DissertationPart2Application.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {};
    }
}