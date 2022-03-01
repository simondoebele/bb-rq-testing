import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomTest implements Serializable {
    int[] arrayVals;
    int key;
    // We aim to restrict our array values in order to certain values,
    // so to have test cases where we find the key.
    public static int min = -20;
    public static int max = 20;

    public RandomTest(int length) {
        arrayVals = generateRandomIntegerArray(length);
        key = generateRandomKey();
    }

    public static int[] generateRandomIntegerArray(int length){
        int[] numbers = new int[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++){
            numbers[i] = rand.nextInt();
        }
        return numbers;
    }

    public static int generateRandomKey(){
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        return (int)Math.floor(Math.random()*(max-min)+min);
    }

    public static ArrayList<RandomTest> generateRandomTestSuite(int lengthOfTestSuite, int lengthOfArray){
        ArrayList<RandomTest> testSuite = new ArrayList<RandomTest>();
        for (int i = 0; i < lengthOfTestSuite; i++ ) {
            testSuite.add(new RandomTest(lengthOfArray));
        }
        return testSuite;
    }

    // write an ArrayList of RandomTests (= the testsuite) to a file
    public static void writeToFile(ArrayList<RandomTest> testSuite, String filename) {
        try(FileOutputStream f = new FileOutputStream(filename);
            ObjectOutput s = new ObjectOutputStream(f)) {
            s.writeObject(testSuite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read an array of arrays (= the testsuite) from a file
    public static ArrayList<RandomTest> readSerialized(String filename) {
        ArrayList<RandomTest> testSuite = null;
        try (FileInputStream in = new FileInputStream(filename);
             ObjectInputStream s = new ObjectInputStream(in)) {
            testSuite = (ArrayList<RandomTest>) s.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return testSuite;
    }
}
