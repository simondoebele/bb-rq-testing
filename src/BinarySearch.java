

import java.util.*;

public class BinarySearch {

    //input: array of values (chosen from ordered set, here: ints), key
    //output: boolean (key occurs -> TRUE, else FALSE)
    // JML for membership
    /*@ requires A.length >= 1;
    @ ensures (\result == true) ==> (\exists int i; 0 <= i && i < A.length; A[i] == key);
    @ ensures (\result == false) ==> !(\exists int i; 0 <= i && i < A.length; A[i] == key);
    @ pure;
    @*/
    public static boolean isMember(int[] A, int key){
        for (int j : A) {
            if (j == key) {
                return true;
            }
        }
        return false;
    }

    // sorts integer arrays of arbitrary length
    //input: array of values (chosen from ordered set, here we assume ints)
    //output: array of same length + elements in ascending order
    // JML for sorting
    /*@ requires A!= null;
    @ ensures A.length == \old(A.length);
    @ ensures (\forall int i,j; 0 <= i & i < j & j < A.length; A[i] <= A[j]);
    @ ensures \forall int j; 0 <= j && j < A.length; (\num_of int i; 0 <= i && i < A.length; A[i] == \old(A[j]) ) == (\num_of int i; 0 <= i && i < A.length; \old(A[i]) == \old A([j]))
    @*/
    public static int[] sortArrayAscending(int[] A){
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                if (A[i] > A[j]){
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }
        return A;
    }

    //  membership queries using binary search
    //input: sorted(!) array of values (chosen from ordered set, here: ints), key (=value that might occur in the array)
    //output: n if key occurs in position n, else -1
    // JML for searching and BinarySearch (no difference between the two, other than binarySearch being of a certain speed requirement)
    /*@ requires A.length >= 1;
    @ requires (\forall int i,j; 0 <= i & i < j & j < A.length; A[i] <= A[j]);
    @ ensures A == \old(A);
    @ ensures (\result >= 0) ==> (\exists int i; 0 <= i && i < A.length; A[i] == \result);
    @ ensures (\result == -1) ==> !(\exists int i; 0 <= i && i < A.length; A[i] == key);
    @*/
    public static int binarySearch(int[] A, int key){
        int left = 0;
        int right = A.length-1;
        int mid = (left+right)/2;

        while (left <= right) {
            if(A[mid]<key){
                left = mid + 1;
            }
            else if (A[mid] == key){
                return mid;
            }
            else {
                right = mid - 1;
            }
            mid = (left+right)/2;
        }
        return -1;
    }

    // combining sorting with binary Search
    public static int sortThenBinarySearch(int[] A, int k){
        return binarySearch(sortArrayAscending(A),k);
    }

    // quick test:
    /*public static void main(String[] args) {
        int[] A = {2,5,1,3,4};
        int key = 1;

        int result = sortThenBinarySearch(A, key);
        System.out.println(result);
    }*/
}
