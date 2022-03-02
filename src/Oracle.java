import java.util.ArrayList;

public class Oracle {
    // => What does Oracle do? Performance measurement: ...
    // ... count the number of test cases executed until error is found.
    // That count will be a minimum count for Pairwise (as the testsuite there is fixed).
    // And an average count for Random (as the sample testsuite there may vary)
    // (i.e. for average count: need to execute multiple times.)

    public static void main(String[] args) {
        int arrayLength = 20;
        // read Random Test Suites:
        // int[] testSuiteLengths = {10,100,500,1000}; <- no need for all
        int[] testSuiteLengths = {100};
        for (int i = 0; i < testSuiteLengths.length; i++){
            String filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            ArrayList<RandomTest> rts = RandomTest.readSerialized(filename);
        }

        // read PairWise Test Suites:
        String filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        ArrayList<PairwiseTest> pts = PairwiseTest.readSerialized(filename);

        // apply tests to binary Search, 6 mutated searches
            // for each test and key -> run bin search
            // count assertions (pass / fail) -> stop at fail, return count.
        // for random:
            // bin search

            // MBS001
            // MBS002
            // MBS003
            // MBS004
            // MBS005
            // MBS006
        // for pairwise:
            // bin search
            // MBS001
            // MBS002
            // MBS003
            // MBS004
            // MBS005
            // MBS006


    }
}
