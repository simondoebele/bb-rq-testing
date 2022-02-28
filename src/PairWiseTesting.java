import java.io.*;
import java.util.*;


public class PairWiseTesting {

  static final int NON_DEFAULT_ONE = 3;
  static final int NON_DEFAULT_TWO = 4;


  public static int[] zeroWise(int length){

    //length+1 for key
    int[] numbers = new int[length+1];

    //Range for array numbers
    int min = -20;
    int max = 20;

    for (int i = 0; i < length+1; i++) {
      numbers[i] = min + (int)(Math.random() * ((max - min) + 1));
    }
    return numbers;
  }

  public static int[][] oneWise(int length) {
    int[][] numbers = new int[length][length + 1]; //length+1 for key

    //Range for array numbers
    int min = -20;
    int max = 20;

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length + 1; j++) {
        numbers[i][j] = min + (int)(Math.random() * ((max - min) + 1));
      }
    }

    //int typical = rand.nextInt();

    //test
    int typical = 1;

    for (int i = 0; i < length; i++) {
      numbers[i][i] = typical;
    }

    return numbers;
  }

  public static int[][] twoWise(int length) {
    int[][] numbers = new int[length * 19][length + 1];

    //Range for array numbers
    int min = -20;
    int max = 20;

    for (int i = 0; i < length * 19; i++) {
      for (int j = 0; j < length + 1; j++) {
        numbers[i][j] = min + (int)(Math.random() * ((max - min) + 1));
      }
    }

    //int typical = rand.nextInt();

    //test
    int typical = 666;
    int typicalB = 444;
    for (int i = 0; i < length * 19; i++) {

      numbers[i][i / 19] = typical;
      if ( (i % 19) < (i / 19) ) {
        numbers[i][i % 19] = typicalB;
      }
      else if ( (i % 19) >= (i / 19)) {
        numbers[i][(i % 19)+1] = typicalB;

      }
    }
    return numbers;
  }

  public static void print(int[][] grid) {
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
    print(twoWise(20));
    //System.out.println(25 / 19);

  }

  // for each input variable -> define the set of typical values
  // we have length of array + 1 (key) input variables.
  // to make the problem easy, have two typical values for each integer: negative, non-negative
  // choose one typical value to be the default value for each input variable:
  // default value for the array elements: (1,2,3,4,5, ..., 20)
  // default value for the key: 4
  // Recursively define:
  // 0-wise: all default values
  // 1-wise: 1 non-default values (aka 1 typical value)
  // 2-wise: 2 non-default values (aka 2 typical values)
  // for 1-wise, 2-wise, ...: what we randomize is which of the default values to make typical.

  // array of all default values. -> 0-wise.
  // 1-wise: for each array location (of length 21), randomize each location for 1 test case -> total: 21 test cases.
  // 2-wise: -> total: 21*20 test cases (fill two slots with non-default values)
  // WRONG, actually 20 * 19 = 380
  // default + non-default values = SET OF TYPICAL VALUES

  /* TO-DO: Generate number of test cases (in experimenter)
   *  randomize which of the default values to make typical
   *  */


}
