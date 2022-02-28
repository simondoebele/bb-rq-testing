import java.io.*;
import java.util.ArrayList;
import java.util.RandomAccess;

// https://stackoverflow.com/questions/28570967/store-java-arrays-to-file-for-read-and-write
public class ReadWriter implements Serializable {
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
