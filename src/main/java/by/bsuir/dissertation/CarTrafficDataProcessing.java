package by.bsuir.dissertation;

import by.bsuir.dissertation.configuration.NeuralNetworkConfiguration;
import by.bsuir.dissertation.entity.exchange.ResponseData;
import by.bsuir.dissertation.util.NormalizeUtils;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class CarTrafficDataProcessing implements LearningEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(CarTrafficDataProcessing.class);

    private MultiLayerPerceptron neuralNet;

    private NeuralNetworkConfiguration neuralNetworkConfiguration;

    @Autowired
    public CarTrafficDataProcessing(NeuralNetworkConfiguration neuralNetworkConfiguration) {
        this.neuralNetworkConfiguration = neuralNetworkConfiguration;
        run();
    }

    private void run() {
        neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, neuralNetworkConfiguration.getInputCount(), neuralNetworkConfiguration.getMiddleCount(), neuralNetworkConfiguration.getOutputCount());
        File file;
        try {
            file = new ClassPathResource(neuralNetworkConfiguration.getNameTrainingSet()).getFile();
        } catch (IOException e) {
            LOGGER.error("", e);
            return;
        }

        LOGGER.info("Training set " + file.getName());

        DataSet trainingSet;
        try {
            trainingSet = TrainingSetImport.importFromFile(file.getAbsolutePath(), neuralNetworkConfiguration.getInputCount(), neuralNetworkConfiguration.getOutputCount(), ",");
        } catch (IOException | NumberFormatException e) {
            LOGGER.error("", e);
            return;
        }

        LOGGER.info("Create network");
        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
        learningRule.addListener(this);
        learningRule.setLearningRate(0.3);
        learningRule.setMomentum(0.6);
        learningRule.setMaxError(0.05);

        LOGGER.info("Training network");
        neuralNet.learn(trainingSet);

        LOGGER.info("Test network");
        trainingSet.getRows().forEach(row -> {
            neuralNet.setInput(row.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

            LOGGER.info("Input: " + Arrays.toString(row.getInput()) + " Output: " + Arrays.toString(networkOutput));
        });
    }

    public ResponseData getCoordinates(String id, Date date) {
        List<Double> input = new ArrayList<>();
        input.add(NormalizeUtils.normalize(NormalizeUtils.getTrueHash(id), 0, Integer.MAX_VALUE));
        int[] daysOfWeek = NormalizeUtils.normalizeDayOfWeek(date);
        for (int day : daysOfWeek) {
            input.add((double) day);
        }
        input.add(NormalizeUtils.normalizeTime(date));
        double[] resultInput = new double[input.size()];
        for (int i = 0; i < resultInput.length; i++) {
            resultInput[i] = input.get(i);
        }
        neuralNet.setInput(resultInput);
        neuralNet.calculate();
        double[] networkOutput = neuralNet.getOutput();
        ResponseData responseData = new ResponseData(NormalizeUtils.denormalize(networkOutput[0], neuralNetworkConfiguration.getMinLatitude(), neuralNetworkConfiguration.getMaxLatitude()),
                NormalizeUtils.denormalize(networkOutput[1], neuralNetworkConfiguration.getMinLongitude(), neuralNetworkConfiguration.getMaxLongitude()));
        LOGGER.info("Input: " + Arrays.toString(resultInput) + " Output: " + Arrays.toString(networkOutput));
        return responseData;
    }

    @Override
    public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation) event.getSource();
        LOGGER.info(bp.getCurrentIteration() + ". iteration | Total network error: " + bp.getTotalNetworkError());
    }
}