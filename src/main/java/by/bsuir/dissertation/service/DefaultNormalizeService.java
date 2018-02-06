package by.bsuir.dissertation.service;

import by.bsuir.dissertation.entity.result.ResultData;
import by.bsuir.dissertation.entity.neuroph.NormalizeRow;
import by.bsuir.dissertation.util.NormalizeUtils;
import by.bsuir.dissertation.repository.ResultDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultNormalizeService implements NormalizeService {

    private ResultDataRepository resultDataRepository;

    @Autowired
    public DefaultNormalizeService(ResultDataRepository resultDataRepository) {
        this.resultDataRepository = resultDataRepository;
    }

    @Override
    public void normalizeDataAndSaveToFile() {
        List<ResultData> resultDataList = resultDataRepository.findAll();
        List<NormalizeRow> normalizeRows = new ArrayList<>();

        resultDataList.forEach(resultData -> {
            double normalizeCarId = NormalizeUtils.normalize(resultData.getCar().hashCode(), 0, Integer.MAX_VALUE);
            resultData.getPartResultData().forEach(partResultData -> {
                NormalizeRow normalizeRow = new NormalizeRow();
                normalizeRow.setCarId(normalizeCarId);
                normalizeRow.setDayOfWeek(NormalizeUtils.normalizeDayOfWeek(partResultData.getDate()));
                normalizeRow.setTime(NormalizeUtils.normalizeTime(partResultData.getDate()));
                normalizeRow.setLatitude(NormalizeUtils.normalize(Double.valueOf(partResultData.getNode().getLatitude()), 53, 54));
                normalizeRow.setLongitude(NormalizeUtils.normalize(Double.valueOf(partResultData.getNode().getLongitude()), 28, 29));
                normalizeRows.add(normalizeRow);
            });
        });

        // TODO: save data to file using method toFileString in NormalizeRow class
        // TODO: use ',' delimiter

        normalizeRows.forEach(normalizeRow -> System.out.println(normalizeRow.toString()));
    }
}
