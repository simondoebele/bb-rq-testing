// Experimenter: to see how large each file needs to be (number of test cases)
// in order to find specific mutation error.

import java.util.ArrayList;

public class Experimenter {

    public static ArrayList<RandomTest> generateRandomTestSuite(int lengthOfTestSuite, int lengthOfArray){
        ArrayList<RandomTest> testSuite = new ArrayList<RandomTest>();
        for (int i = 0; i < lengthOfTestSuite; i++ ) {
            testSuite.add(new RandomTest (lengthOfArray));
        }
        return testSuite;
    }

    public static ArrayList<PairwiseTest> generatePairwiseTestSuite(int lengthOfTestSuite, int lengthOfArray) {
        // TODO: write function
        return null;
    }


    public static void main(String[] args) {
        int arrayLength = 20;
        // create Random Test Suites and write to file:
        int[] testSuiteLengths = {10,100,500,1000};
        for (int i = 0; i < testSuiteLengths.length; i++){
            ArrayList<RandomTest> ts = generateRandomTestSuite(testSuiteLengths[i],arrayLength);
            String filename = "RandomTestsuite_" + testSuiteLengths[i] + ".txt";
            ReadWriter.writeToFile(ts, filename);
        }
        // TODO: create PairWise Test Suites and write to file:

        // TODO: experiment with random test suite sizes
    }


}
