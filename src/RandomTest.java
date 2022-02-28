import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomTest {
    int[] arrayVals;
    int key;

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
        int min = Integer.MIN_VALUE; // shall we even test for overflow / underflow?
        int max = Integer.MAX_VALUE;
        return (int)Math.floor(Math.random()*(max-min)+min);
    }

    public static Map<int[], Integer> generateRandomTest(int length){
        int key = generateRandomKey();
        int[] array = generateRandomIntegerArray((length));
        Map<int[], Integer> map = new HashMap<int[], Integer>();
        map.put(array, key);
        return map;
    }

}
