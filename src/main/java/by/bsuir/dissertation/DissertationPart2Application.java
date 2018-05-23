package by.bsuir.dissertation;

import by.bsuir.dissertation.service.NormalizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DissertationPart2Application {

    private final static Logger LOGGER = LoggerFactory.getLogger(DissertationPart2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(DissertationPart2Application.class, args);
    }

//    @Bean
//    public CommandLineRunner run(NormalizeService normalizeService) {
//        return args -> {
//            LOGGER.info("START WORK");
//            normalizeService.normalizeDataAndSaveToFile();
//        };
//    }
}