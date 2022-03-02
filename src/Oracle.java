import java.util.ArrayList;
import static com.google.common.primitives.Ints.contains;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

public class Oracle {
    // => What does Oracle do? Performance measurement: ...
    // ... count the number of test cases executed until error is found.
    // That count will be a minimum count for Pairwise (as the testsuite there is fixed).
    // And an average count for Random (as the sample testsuite there may vary)
    // (i.e. for average count: need to execute multiple times.)

    public static boolean testSortExpectTrue(int[] supposedlySortedArray) {
        for (int i = 0; i < supposedlySortedArray.length - 1; i++) {
            if (supposedlySortedArray[i] > supposedlySortedArray[i+1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean testSearch(int[] supposedlySortedArray, int key) {
        boolean contained = contains(supposedlySortedArray, key); //ground truth
        // compare ground truth against our method: our method should conclude the same as the ground truth
        if (contained){
            return (BinarySearch.sortThenBinarySearch(supposedlySortedArray, key) >= 0);
        }
        else {
            return (BinarySearch.sortThenBinarySearch(supposedlySortedArray, key) == -1);
        }
    }

    public static void main(String[] args) {
        int arrayLength = 20;
        // read Random Test Suites:
        // int[] testSuiteLengths = {10,100,500,1000}; <- no need for all
        int[] testSuiteLengths = {100};
        ArrayList<RandomTest> rts = null;
        for (int i = 0; i < testSuiteLengths.length; i++) {
            String filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            rts = RandomTest.readSerialized(filename);
        }

        // read PairWise Test Suites:
        String filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        ArrayList<PairwiseTest> pts = PairwiseTest.readSerialized(filename);

        // apply tests to binary Search, 6 mutated searches
        // for each test and key -> run bin search
        // count assertions (pass / fail) -> stop at fail, return count.

        // bin search -> compare random and pairwise
        // BinSearch -> random
        int numberOfRandomTests = 0;

        for (int i = 0; i < rts.size(); i++) {
            RandomTest rTestDatum = rts.get(i);
            BinarySearch.sortArrayAscending(rTestDatum.arrayVals);
            if (!testSortExpectTrue(rTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            BinarySearch.sortThenBinarySearch(rTestDatum.arrayVals, rTestDatum.key);
            if (!testSearch(rTestDatum.arrayVals, rTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfRandomTests++;
        }
        System.out.println("Number of tests: Binary Search (Random): "+ numberOfRandomTests); // all tests should pass.

        // BinSearch -> pairwise
        int numberOfPairWiseTests = 0;
        for (int i = 0; i < pts.size(); i++) {
            PairwiseTest pTestDatum = pts.get(i);
            BinarySearch.sortArrayAscending(pTestDatum.arrayVals);
            if (!testSortExpectTrue(pTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            BinarySearch.sortThenBinarySearch(pTestDatum.arrayVals, pTestDatum.key);
            if (!testSearch(pTestDatum.arrayVals, pTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfPairWiseTests++;
        }
        System.out.println("Number of tests: Binary Search (PairWise): "+ numberOfPairWiseTests); // all tests should pass.
        System.out.println("================================================="); // all tests should pass.
        /*for each array from above (random and pairwise)
        BinarySearch.sortArrayAscending(arr);
        sort it
        check whether it is sorted;
        search for key;
        check whether key is correctly found/not found
        count the number of passing Tests (until it fails)*/

        // MBS001 -> compare random and pairwise
        //// need a copy of each of the testsuites, otherwise they will be changed in place!
        // make a new copy by reading in the data again.
        rts = null;
        for (int i = 0; i < testSuiteLengths.length; i++) {
            filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            rts = RandomTest.readSerialized(filename);
        }
        // read PairWise Test Suites:
        filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        pts = PairwiseTest.readSerialized(filename);

        // -> random
        numberOfRandomTests = 0;

        for (int i = 0; i < rts.size(); i++) {
            RandomTest rTestDatum = rts.get(i);
            MutatedBinarySearch001.sortArrayAscending(rTestDatum.arrayVals);
            if (!testSortExpectTrue(rTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch001.sortThenBinarySearch(rTestDatum.arrayVals, rTestDatum.key);
            if (!testSearch(rTestDatum.arrayVals, rTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfRandomTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 001 (Random): "+ numberOfRandomTests);
        System.out.println("--------------------");

        // -> pairwise
        numberOfPairWiseTests = 0;
        for (int i = 0; i < pts.size(); i++) {
            PairwiseTest pTestDatum = pts.get(i);
            MutatedBinarySearch001.sortArrayAscending(pTestDatum.arrayVals);
            if (!testSortExpectTrue(pTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch001.sortThenBinarySearch(pTestDatum.arrayVals, pTestDatum.key);
            if (!testSearch(pTestDatum.arrayVals, pTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfPairWiseTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 001 (PairWise): "+ numberOfPairWiseTests);
        System.out.println("=================================================");





        // MBS002-> compare random and pairwise
        // make a new copy by reading in the data again.
        rts = null;
        for (int i = 0; i < testSuiteLengths.length; i++) {
            filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            rts = RandomTest.readSerialized(filename);
        }
        // read PairWise Test Suites:
        filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        pts = PairwiseTest.readSerialized(filename);

        // -> random
        numberOfRandomTests = 0;

        for (int i = 0; i < rts.size(); i++) {
            RandomTest rTestDatum = rts.get(i);
            MutatedBinarySearch002.sortArrayAscending(rTestDatum.arrayVals);
            if (!testSortExpectTrue(rTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch002.sortThenBinarySearch(rTestDatum.arrayVals, rTestDatum.key);
            if (!testSearch(rTestDatum.arrayVals, rTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfRandomTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 002 (Random): "+ numberOfRandomTests);
        System.out.println("--------------------");

        // -> pairwise
        numberOfPairWiseTests = 0;
        for (int i = 0; i < pts.size(); i++) {
            PairwiseTest pTestDatum = pts.get(i);
            MutatedBinarySearch002.sortArrayAscending(pTestDatum.arrayVals);
            if (!testSortExpectTrue(pTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch002.sortThenBinarySearch(pTestDatum.arrayVals, pTestDatum.key);
            if (!testSearch(pTestDatum.arrayVals, pTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfPairWiseTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 002 (PairWise): "+ numberOfPairWiseTests);
        System.out.println("=================================================");







        // MBS003-> compare random and pairwise
        // make a new copy by reading in the data again.
        rts = null;
        for (int i = 0; i < testSuiteLengths.length; i++) {
            filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            rts = RandomTest.readSerialized(filename);
        }
        // read PairWise Test Suites:
        filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        pts = PairwiseTest.readSerialized(filename);

        // -> random
        numberOfRandomTests = 0;

        for (int i = 0; i < rts.size(); i++) {
            RandomTest rTestDatum = rts.get(i);
            MutatedBinarySearch003.sortArrayAscending(rTestDatum.arrayVals);
            if (!testSortExpectTrue(rTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch003.sortThenBinarySearch(rTestDatum.arrayVals, rTestDatum.key);
            if (!testSearch(rTestDatum.arrayVals, rTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfRandomTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 003 (Random): "+ numberOfRandomTests);
        System.out.println("--------------------");

        // -> pairwise
        numberOfPairWiseTests = 0;
        for (int i = 0; i < pts.size(); i++) {
            PairwiseTest pTestDatum = pts.get(i);
            MutatedBinarySearch003.sortArrayAscending(pTestDatum.arrayVals);
            if (!testSortExpectTrue(pTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch003.sortThenBinarySearch(pTestDatum.arrayVals, pTestDatum.key);
            if (!testSearch(pTestDatum.arrayVals, pTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfPairWiseTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 003 (PairWise): "+ numberOfPairWiseTests);
        System.out.println("=================================================");





        // MBS004-> compare random and pairwise
        // make a new copy by reading in the data again.
        rts = null;
        for (int i = 0; i < testSuiteLengths.length; i++) {
            filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            rts = RandomTest.readSerialized(filename);
        }
        // read PairWise Test Suites:
        filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        pts = PairwiseTest.readSerialized(filename);

        // -> random
        numberOfRandomTests = 0;

        for (int i = 0; i < rts.size(); i++) {
            RandomTest rTestDatum = rts.get(i);
            MutatedBinarySearch004.sortArrayAscending(rTestDatum.arrayVals);
            if (!testSortExpectTrue(rTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch004.sortThenBinarySearch(rTestDatum.arrayVals, rTestDatum.key);
            if (!testSearch(rTestDatum.arrayVals, rTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfRandomTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 004 (Random): "+ numberOfRandomTests);
        System.out.println("--------------------");

        // -> pairwise
        numberOfPairWiseTests = 0;
        for (int i = 0; i < pts.size(); i++) {
            PairwiseTest pTestDatum = pts.get(i);
            MutatedBinarySearch004.sortArrayAscending(pTestDatum.arrayVals);
            if (!testSortExpectTrue(pTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch004.sortThenBinarySearch(pTestDatum.arrayVals, pTestDatum.key);
            if (!testSearch(pTestDatum.arrayVals, pTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfPairWiseTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 004 (PairWise): "+ numberOfPairWiseTests);
        System.out.println("=================================================");







        // MBS005-> compare random and pairwise
        // make a new copy by reading in the data again.
        rts = null;
        for (int i = 0; i < testSuiteLengths.length; i++) {
            filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            rts = RandomTest.readSerialized(filename);
        }
        // read PairWise Test Suites:
        filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        pts = PairwiseTest.readSerialized(filename);

        // -> random
        numberOfRandomTests = 0;

        for (int i = 0; i < rts.size(); i++) {
            RandomTest rTestDatum = rts.get(i);
            MutatedBinarySearch005.sortArrayAscending(rTestDatum.arrayVals);
            if (!testSortExpectTrue(rTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch005.sortThenBinarySearch(rTestDatum.arrayVals, rTestDatum.key);
            if (!testSearch(rTestDatum.arrayVals, rTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfRandomTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 005 (Random): "+ numberOfRandomTests);
        System.out.println("--------------------");

        // -> pairwise
        numberOfPairWiseTests = 0;
        for (int i = 0; i < pts.size(); i++) {
            PairwiseTest pTestDatum = pts.get(i);
            MutatedBinarySearch005.sortArrayAscending(pTestDatum.arrayVals);
            if (!testSortExpectTrue(pTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch005.sortThenBinarySearch(pTestDatum.arrayVals, pTestDatum.key);
            if (!testSearch(pTestDatum.arrayVals, pTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfPairWiseTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 005 (PairWise): "+ numberOfPairWiseTests);
        System.out.println("=================================================");







        // MBS006-> compare random and pairwise
        // make a new copy by reading in the data again.
        rts = null;
        for (int i = 0; i < testSuiteLengths.length; i++) {
            filename = "RandomTestsuite_Length" + testSuiteLengths[i] + "_ArrayLength_" + arrayLength + ".txt";
            rts = RandomTest.readSerialized(filename);
        }
        // read PairWise Test Suites:
        filename = "PairwiseTestsuite_" + "ArrayLength_" + arrayLength + ".txt";
        pts = PairwiseTest.readSerialized(filename);

        // -> random
        numberOfRandomTests = 0;

        for (int i = 0; i < rts.size(); i++) {
            RandomTest rTestDatum = rts.get(i);
            MutatedBinarySearch006.sortArrayAscending(rTestDatum.arrayVals);
            if (!testSortExpectTrue(rTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch006.sortThenBinarySearch(rTestDatum.arrayVals, rTestDatum.key);
            if (!testSearch(rTestDatum.arrayVals, rTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfRandomTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 006 (Random): "+ numberOfRandomTests);
        System.out.println("--------------------");

        // -> pairwise
        numberOfPairWiseTests = 0;
        for (int i = 0; i < pts.size(); i++) {
            PairwiseTest pTestDatum = pts.get(i);
            MutatedBinarySearch006.sortArrayAscending(pTestDatum.arrayVals);
            if (!testSortExpectTrue(pTestDatum.arrayVals)){
                System.out.println("Sort failed.");
                break;
            }
            MutatedBinarySearch006.sortThenBinarySearch(pTestDatum.arrayVals, pTestDatum.key);
            if (!testSearch(pTestDatum.arrayVals, pTestDatum.key)){
                System.out.println("Search failed.");
                break;
            }
            numberOfPairWiseTests++;
        }
        System.out.println("Number of tests: Mutated Binary Search 006 (PairWise): "+ numberOfPairWiseTests);
        System.out.println("=================================================");

        // TODO: the testsuites done anew, will give new test cases. Then it's not a fair comparison...
        // hence: need to do a deep copy of the array list objects...
    }
}
