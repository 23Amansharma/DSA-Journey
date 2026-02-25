import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        //Box the primitives into Objects to use custom Comparators
        Integer[] boxedArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedArr[i] = arr[i];
        }
        
        // Sort with a custom lambda comparator
        Arrays.sort(boxedArr, (a, b) -> {
            int bitCountA = Integer.bitCount(a);
            int bitCountB = Integer.bitCount(b);
            
            if (bitCountA == bitCountB) {
                // If bits are equal, sort by natural value
                return a.compareTo(b);
            }
            // Otherwise, sort by bit count
            return Integer.compare(bitCountA, bitCountB);
        });
        
        // Unbox back to the original primitive array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = boxedArr[i];
        }
        
        return arr;
    }
}