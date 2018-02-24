package by.bsuir.dissertation;

import by.bsuir.dissertation.entity.neuroph.RequestData;
import by.bsuir.dissertation.entity.neuroph.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class DissertationPart2Application {

    private final static Logger LOGGER = LoggerFactory.getLogger(CarTrafficDataTest.class);

    public static void main(String[] args) {
        SpringApplication.run(DissertationPart2Application.class, args);
    }

    @Bean
    public CommandLineRunner run(CarTrafficDataTest carTrafficDataTest) {
        return args -> {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            ResponseData responseData = carTrafficDataTest.getCoordinates(new RequestData("7d389e3b-5316-45eb-87bf-87ac72bfcb9c", formatter.parse("2018-02-04 11:08:49.601")));
            LOGGER.error(responseData.toString());
        };
    }
}