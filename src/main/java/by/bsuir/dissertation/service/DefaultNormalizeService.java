package by.bsuir.dissertation.service;

import by.bsuir.dissertation.configuration.NeuralNetworkConfiguration;
import by.bsuir.dissertation.entity.neuroph.NormalizeRow;
import by.bsuir.dissertation.entity.result.ResultData;
import by.bsuir.dissertation.repository.ResultDataRepository;
import by.bsuir.dissertation.util.NormalizeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultNormalizeService implements NormalizeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultNormalizeService.class);

    private ResultDataRepository resultDataRepository;

    private NeuralNetworkConfiguration neuralNetworkConfiguration;

    @Autowired
    public DefaultNormalizeService(ResultDataRepository resultDataRepository, NeuralNetworkConfiguration neuralNetworkConfiguration) {
        this.resultDataRepository = resultDataRepository;
        this.neuralNetworkConfiguration = neuralNetworkConfiguration;
    }

    @Override
    public void normalizeDataAndSaveToFile() {
        List<ResultData> resultDataList = resultDataRepository.findAll();
        List<NormalizeRow> normalizeRows = new ArrayList<>();

        resultDataList.forEach(resultData -> {
            double normalizeCarId = NormalizeUtils.normalize(NormalizeUtils.getTrueHash(resultData.getCar().getId()), 0, Integer.MAX_VALUE);
            resultData.getPartResultData().forEach(partResultData -> {
                NormalizeRow normalizeRow = new NormalizeRow();
                normalizeRow.setCarId(normalizeCarId);
                normalizeRow.setDayOfWeek(NormalizeUtils.normalizeDayOfWeek(partResultData.getDate()));
                normalizeRow.setTime(NormalizeUtils.normalizeTime(partResultData.getDate()));
                normalizeRow.setLatitude(NormalizeUtils.normalize(Double.valueOf(partResultData.getNode().getLatitude()), neuralNetworkConfiguration.getMinLatitude(), neuralNetworkConfiguration.getMaxLatitude()));
                normalizeRow.setLongitude(NormalizeUtils.normalize(Double.valueOf(partResultData.getNode().getLongitude()), neuralNetworkConfiguration.getMinLongitude(), neuralNetworkConfiguration.getMaxLongitude()));
                normalizeRows.add(normalizeRow);
            });
        });
        saveToFile(normalizeRows);
        normalizeRows.forEach(normalizeRow -> System.out.println(normalizeRow.toString()));
    }

    private void saveToFile(List<NormalizeRow> normalizeRows) {
        String fileLocation = new File(neuralNetworkConfiguration.getPathToTrainingSet()).getAbsolutePath();
        Path path = Paths.get(fileLocation);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (NormalizeRow normalizeRow : normalizeRows) {
                writer.write(normalizeRow.toFileString(neuralNetworkConfiguration.getDelimiter()));
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.error("Method saveToFile doesn't work.", e);
        }
    }
}
