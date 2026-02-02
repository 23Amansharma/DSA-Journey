import java.util.Arrays;

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        //Array ko sort kara
        Arrays.sort(arr);
        
        //  Pehle do elements ka difference nikaala
        int diff = arr[1] - arr[0];
        
        // Check kara ki baaki saare elements mein bhi wahi difference hai
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false; // Agar gap alag mila toh false
            }
        }
        
        return true; // Sab gap same hain toh true
    }
}