package by.bsuir.dissertation.service;

import by.bsuir.dissertation.configuration.NeuralNetworkConfiguration;
import by.bsuir.dissertation.entity.neuroph.Data;
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
        List<Data> data = new ArrayList<>();
        resultDataList.forEach(resultData -> {
            double normalizeCarId = NormalizeUtils.normalize(NormalizeUtils.getTrueHash(resultData.getCar().getId()), 0, Integer.MAX_VALUE);

            resultData.getPartResultData().forEach(partResultData -> {
                NormalizeRow normalizeRow = new NormalizeRow();
                normalizeRow.setCarId(normalizeCarId);

                normalizeRow.setDayOfWeek(NormalizeUtils.normalizeDayOfWeek(partResultData.getDate()));
                double v = NormalizeUtils.normalizeTime(partResultData.getDate());
                normalizeRow.setTime(v);
                double normalizeLatitude = NormalizeUtils.normalize(Double.valueOf(partResultData.getNode().getLatitude()), neuralNetworkConfiguration.getMinLatitude(), neuralNetworkConfiguration.getMaxLatitude());
                normalizeRow.setLatitude(normalizeLatitude);
                double normalizeLongitude = NormalizeUtils.normalize(Double.valueOf(partResultData.getNode().getLongitude()), neuralNetworkConfiguration.getMinLongitude(), neuralNetworkConfiguration.getMaxLongitude());
                normalizeRow.setLongitude(normalizeLongitude);
                Data data1 = new Data(resultData.getCar().getId(), normalizeCarId, partResultData.getDate(), v, partResultData.getNode().getLatitude(), normalizeLatitude, partResultData.getNode().getLongitude(), normalizeLongitude);
                data.add(data1);
                normalizeRows.add(normalizeRow);
            });
        });
        saveToFile(normalizeRows);
        normalizeRows.forEach(normalizeRow -> System.out.println(normalizeRow.toString()));
        saveToFileIDtoNormalizeDate(data);
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

    private void saveToFileIDtoNormalizeDate(List<Data> data) {
        String fileLocation = new File(neuralNetworkConfiguration.getPathnew()).getAbsolutePath();
        Path path = Paths.get(fileLocation);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Data data1 : data) {
                writer.write(data1.toFileString(neuralNetworkConfiguration.getDelimiter()));
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.error("Method saveToFile doesn't work.", e);
        }
    }
}
