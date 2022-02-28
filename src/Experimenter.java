// Experimenter: to see how large each file needs to be (number of test cases)
// in order to find specific mutation error.

import java.util.Random;
import RandomTestsuite;
//import PairwiseTestsuite;

public class Experimenter {

    public static int[][] generateRandomTestSuite(int lengthOfTestSuite, int lengthOfArray){
        int[][] testSuite = new int[][];
        Random rand = new Random();
        for (int i = 0; i < lengthOfTestSuite; i++){
            testSuite.append(generateRandomTest(lengthOfArray));
        }
        return testSuite;
    }

for (lengthOfTestSuite in 10,100,500,1000){
        a = generateRandomTestSuite(lengthOfTestSuite, 20)
        writeToFile(a, filename = "lengthOfTestSuite.txt")
    }

// public static int[] generatePairwiseTestSuite(int lengthOfTestSuite, int lengthOfArray){
//
// }

}
