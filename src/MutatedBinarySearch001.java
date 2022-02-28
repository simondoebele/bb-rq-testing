import java.util.Arrays;

public class MutatedBinarySearch001 {

    // pure helper method for sortArrayAscending method.
    /*@ pure @*/
    public static boolean hasSameElements(int[] A, int[] B){
        // create clones to avoid sorting in place.
        int[] tempA = A.clone();
        int[] tempB = B.clone();
        Arrays.sort(tempA);
        Arrays.sort(tempB);
        return Arrays.equals(tempA,tempB);
    }

    //input: array of values (chosen from ordered set, here: ints), key
    //output: boolean (key occurs -> TRUE, else FALSE)
    // 2 iii) JML for membership
    /*@ requires A.length >= 1;
    @ ensures (\result == true) ==> (\exists int i; 0 <= i && i < A.length; A[i] == key);
    @ ensures (\result == false) ==> !(\exists int i; 0 <= i && i < A.length; A[i] == key);
    @ pure;
    @*/
    public static boolean isMember(int[] A, int key){
        for (int i = 0; i < A.length; i++) {
            if (A[i] == key){
                return true;
            }
        }
        return false;
    }

    // 3 i) sorts integer arrays of arbitrary length
    //input: array of values (chosen from ordered set, here we assume ints)
    //output: array of same length + elements in ascending order
    // 2 i) JML for sorting
    /*@ requires A.length >= 1;
    @ ensures A.length == \old(A.length);
    @ ensures (\forall int i,j; 0 <= i & i < j & j < A.length; A[i] <= A[j]);
    @ ensures hasSameElements(A, \old(A));
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

    // 3 ii) membership queries using binary search
    //input: sorted(!) array of values (chosen from ordered set, here: ints), key (=value that might occur in the array)
    //output: n if key occurs in position n, else -1
    // 2 ii) + iv) JML for searching and BinarySearch (no difference between the two, other than binarySearch being of a certain speed requirement)
    /*@ requires A.length >= 1;
    @ requires (\forall int i,j; 0 <= i & i < j & j < A.length; A[i] <= A[j]);
    @ ensures A == \old(A);
    @ ensures (isMember(A, key) == true) ==> (\exists int i; 0 <= i && i < A.length; A[i] == \result);
    @ ensures (\result == -1) ==> !(\exists int i; 0 <= i && i < A.length; A[i] == key);
    @*/
    public static int binarySearch(int[] A, int key){
        int left = 0;
        int right = A.length;
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

    // 3 iii) combining sorting with binary Search
    public static int sortThenBinarySearch(int[] A, int k){
        return binarySearch(sortArrayAscending(A),k);
    }


}
