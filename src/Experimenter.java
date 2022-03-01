// Experimenter: to see how large each file needs to be (number of test cases)
// in order to find specific mutation error.

import java.util.ArrayList;
import java.util.Arrays;

public class Experimenter {

    public static void main(String[] args) {
        int arrayLength = 20;
        // create Random Test Suites and write to file:
        int[] testSuiteLengths = {10,100,500,1000};
        for (int i = 0; i < testSuiteLengths.length; i++){
            ArrayList<RandomTest> rts = RandomTest.generateRandomTestSuite(testSuiteLengths[i],arrayLength);
            printRandomTestsuite(rts);
            String filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            RandomTest.writeToFile(rts, filename);
        }

        // create PairWise Test Suites and write to file:
        ArrayList<PairwiseTest> pts = PairwiseTest.generatePairwiseTestSuite(arrayLength);
        printPairwiseTestsuite(pts);
        String filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        PairwiseTest.writeToFile(pts, filename);
        // TODO: experiment with random test suite sizes

    }

    public static void printPairwiseTestsuite(ArrayList<PairwiseTest> testSuite) {
        for (int i = 0; i < testSuite.size(); i++) {
            PairwiseTest test = testSuite.get(i);
            System.out.println(Arrays.toString(test.arrayVals));
            System.out.println("KEY: " + test.key);
            System.out.println();
        }
    }

    public static void printRandomTestsuite(ArrayList<RandomTest> testSuite) {
        for (int i = 0; i < testSuite.size(); i++) {
            RandomTest test = testSuite.get(i);
            System.out.println(Arrays.toString(test.arrayVals));
            System.out.println("KEY: " + test.key);
            System.out.println();
        }
    }


}
