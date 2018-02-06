package by.bsuir.dissertation;

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
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class RouteDataTest implements LearningEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(RouteDataTest.class);

    public void run() {
        File file;
        try {
            file = new ClassPathResource("CarEvaluation.txt").getFile();
        } catch (IOException e) {
            LOGGER.error("", e);
            return;
        }
        int inputsCount = 21;
        int outputsCount = 4;

        LOGGER.info("Training set " + file.getName());

        DataSet trainingSet;
        try {
            trainingSet = TrainingSetImport.importFromFile(file.getAbsolutePath(), inputsCount, outputsCount, ",");
        } catch (IOException | NumberFormatException e) {
            LOGGER.error("", e);
            return;
        }

        LOGGER.info("Create network");
        MultiLayerPerceptron neuralNet = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 21, 14, 4);
        MomentumBackpropagation learningRule = (MomentumBackpropagation) neuralNet.getLearningRule();
        learningRule.addListener(this);
        learningRule.setLearningRate(0.3);
        learningRule.setMomentum(0.6);
        learningRule.setMaxError(0.05);

//        BackPropagation backPropagation = new BackPropagation();
//        backPropagation.setMaxIterations(1000);
        LOGGER.info("Training network");
//        neuralNet.learn(trainingSet, backPropagation);
        neuralNet.learn(trainingSet);

        LOGGER.info("Test network");
        trainingSet.getRows().forEach(row -> {
            neuralNet.setInput(row.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

            // rounding up the results to an integer
            double[] networkOutputRound = new double[networkOutput.length];
            for (int i = 0; i < networkOutput.length; i++) {
                networkOutputRound[i] = Math.round(networkOutput[i]);
            }
            LOGGER.info("Input: " + Arrays.toString(row.getInput()) + " Output: " + Arrays.toString(networkOutputRound));
        });
    }

    @Override
    public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation) event.getSource();
        LOGGER.info(bp.getCurrentIteration() + ". iteration | Total network error: " + bp.getTotalNetworkError());
    }
}