// Experimenter: to see how large each file needs to be (number of test cases)
// in order to find specific mutation error.

import java.util.ArrayList;
import java.util.Random;

public class Experimenter {

    public static void main(String[] args) {
        int arrayLength = 20;
        // create Random Test Suites and write to file:
        int[] testSuiteLengths = {10,100,500,1000};
        for (int i = 0; i < testSuiteLengths.length; i++){
            ArrayList<RandomTest> rts = RandomTest.generateRandomTestSuite(testSuiteLengths[i],arrayLength);
            String filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "ArrayLength_" + arrayLength + ".txt";
            RandomTest.writeToFile(rts, filename);
        }
        // TODO: create PairWise Test Suites and write to file:
        ArrayList<PairwiseTest> pts = PairwiseTest.generatePairwiseTestSuite(arrayLength);
        String filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        PairwiseTest.writeToFile(pts, filename);
        // TODO: experiment with random test suite sizes

    }


}
