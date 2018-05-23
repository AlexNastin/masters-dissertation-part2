package by.bsuir.dissertation.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Stepanov Nickita on 18.02.2018.
 * @version 1.0
 */
@Configuration
public class NeuralNetworkConfiguration {

    @Value("${neural.network.input}")
    private Integer inputCount;

    @Value("${neural.network.middle}")
    private Integer middleCount;

    @Value("${neural.network.output}")
    private Integer outputCount;

    @Value("${neural.network.trainingset.name}")
    private String nameTrainingSet;

    @Value("${neural.network.trainingset.path}")
    private String pathToTrainingSet;

    @Value("${neural.network.trainingset.namenew}")
    private String namenew;

    @Value("${neural.network.trainingset.pathnew}")
    private String pathnew;

    @Value("${neural.network.delimiter}")
    private String delimiter;

    @Value("${neural.network.trainingset.latitude.min}")
    private Integer minLatitude;

    @Value("${neural.network.trainingset.latitude.max}")
    private Integer maxLatitude;

    @Value("${neural.network.trainingset.longitude.min}")
    private Integer maxLongitude;

    @Value("${neural.network.trainingset.longitude.max}")
    private Integer minLongitude;

    public Integer getInputCount() {
        return inputCount;
    }

    public Integer getMiddleCount() {
        return middleCount;
    }

    public Integer getOutputCount() {
        return outputCount;
    }

    public String getNameTrainingSet() {
        return nameTrainingSet;
    }

    public String getPathToTrainingSet() {
        return pathToTrainingSet;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public Integer getMinLatitude() {
        return minLatitude;
    }

    public Integer getMaxLatitude() {
        return maxLatitude;
    }

    public Integer getMaxLongitude() {
        return maxLongitude;
    }

    public Integer getMinLongitude() {
        return minLongitude;
    }

    public String getNamenew() {
        return namenew;
    }

    public String getPathnew() {
        return pathnew;
    }
}
