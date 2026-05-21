import java.util.HashSet;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> prefixes = new HashSet<>();
        
        //  Add all possible prefixes of numbers in arr1 to the HashSet
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10; // Remove the last digit to get the next prefix
            }
        }
        
        int maxLength = 0;
        
        //  Check prefixes of numbers in arr2 against the HashSet
        for (int num : arr2) {
            while (num > 0) {
                if (prefixes.contains(num)) {
                    //  If found, calculate length and update maxLength
                    int currentLength = String.valueOf(num).length();
                    maxLength = Math.max(maxLength, currentLength);
                    // Since we are going from longest to shortest prefix for this 'num',
                    // we can break once the first (longest) match is found.
                    break; 
                }
                num /= 10;
            }
        }
        
        return maxLength;
    }
}