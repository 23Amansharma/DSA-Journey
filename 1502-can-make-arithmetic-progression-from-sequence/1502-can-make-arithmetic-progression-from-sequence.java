import java.util.Arrays;

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        //  Sort array
        Arrays.sort(arr);
        
        //  Calculate-initial difference
        int diff = arr[1] - arr[0];
        
        // Verify-difference for all consecutive pairs
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        
        return true;
    }
}