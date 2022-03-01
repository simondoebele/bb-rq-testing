import java.io.*;
import java.util.*;

public class PairwiseTest implements Serializable {
  int[] arrayVals;
  int key;
  //Range for array values
  public static int min = -20;
  public static int max = 20;
  // typical values for one and two wise
  public static int typical = 1;
  public static int typicalB = 2;

  public PairwiseTest(int[] arrayVals) {
    this.arrayVals = arrayVals;
    this.key = RandomTest.generateRandomKey();
  }

  public static ArrayList<PairwiseTest> generatePairwiseTestSuite(int lengthOfArray) {
    ArrayList<PairwiseTest> testSuite = new ArrayList<PairwiseTest>();
    // add zero wise
    int[] arrayVals = zeroWise(lengthOfArray);
    testSuite.add(new PairwiseTest(arrayVals));
    // add one wise
    testSuite.addAll(oneWise(lengthOfArray));
    // add two wise
    testSuite.addAll(twoWise(lengthOfArray));
    //
    return testSuite;
  }

  public static int[] zeroWise(int length){
    int[] zeroWiseArray = new int[length];
    for (int i = 0; i < length; i++) {
      zeroWiseArray[i] = min + (int)(Math.random() * ((max - min) + 1));
    }
    return zeroWiseArray;
  }

  public static ArrayList<PairwiseTest> oneWise(int length) {
    ArrayList<PairwiseTest> testSuite = new ArrayList<PairwiseTest>();
    for (int i = 0; i < length; i++) {
      int[] oneWiseArray = zeroWise(length);
      oneWiseArray[i] = typical;
      testSuite.add(new PairwiseTest(oneWiseArray));
    }
    return testSuite;
  }

  public static ArrayList<PairwiseTest> twoWise(int length) {
    ArrayList<PairwiseTest> testSuite = new ArrayList<PairwiseTest>();
    for (int i = 0; i < length; i++) {
      int[] oneWiseArray = zeroWise(length);
      oneWiseArray[i] = typical;
      for (int j = 0; j < length; j++) {
        if (j != i) {
          int[] twoWiseArray = oneWiseArray.clone();
          twoWiseArray[j] = typicalB;
          testSuite.add(new PairwiseTest(twoWiseArray));
        }
      }
    }
    return testSuite;
  }

  // write an ArrayList of PairwiseTests (= the testsuite) to a file
  public static void writeToFile(ArrayList<PairwiseTest> testSuite, String filename) {
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
  public static ArrayList<PairwiseTest> readSerialized(String filename) {
    ArrayList<PairwiseTest> testSuite = null;
    try (FileInputStream in = new FileInputStream(filename);
         ObjectInputStream s = new ObjectInputStream(in)) {
      testSuite = (ArrayList<PairwiseTest>) s.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return testSuite;
  }

  /*public static void print(int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      System.out.print(i+1 + ") [");
      if(grid[i].length >= 1) {
        System.out.print(grid[i][0]);
      }
      for (int j = 1; j < grid[i].length-1; j++) {
        System.out.print(", " + grid[i][j]);
      }
      System.out.println("] KEY: " + grid[i][grid[i].length-1]);

      System.out.println();
    }
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(zeroWise(20)));
    System.out.println("------------------------------------------------------------------------------------------------------");
    //System.out.println(Arrays.deepToString(oneWise(20)));
    // print(twoWise(20));
    //System.out.println(25 / 19);

  }*/

}
