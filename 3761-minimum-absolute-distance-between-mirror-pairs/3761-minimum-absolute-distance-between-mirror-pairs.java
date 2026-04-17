import java.util.HashMap;
import java.util.Map;

class Solution {

    public int minMirrorPairDistance(int[] nums) {
        // Edge case: We need at least 2 numbers to make a pair
        if (nums == null || nums.length < 2) {
            return -1;
        }

        // This acts as our "notebook"
        // It remembers: [Reversed Number -> The Latest Index Where We Saw It]
        Map<Integer, Integer> seenReversedNumbers = new HashMap<>();
        
        // Start with the maximum possible distance
        int smallestDistance = Integer.MAX_VALUE;

        // Walk through the array one by one
        for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
            int currentNumber = nums[currentIndex];
            
            // Step 1: Check if the current number matches a previously saved reversed number
            if (seenReversedNumbers.containsKey(currentNumber)) {
                // We found a match! Get the index of that previous number
                int previousIndex = seenReversedNumbers.get(currentNumber);
                
                // Calculate the distance
                int distance = currentIndex - previousIndex;
                
                // Keep the smallest distance we have found so far
                smallestDistance = Math.min(smallestDistance, distance);
            }
            
            // Step 2: Reverse the current number and save it in the notebook for the future
            int reversedNumber = reverseNumber(currentNumber);
            seenReversedNumbers.put(reversedNumber, currentIndex);
        }

        // If our smallest distance never changed, it means we found zero pairs
        if (smallestDistance == Integer.MAX_VALUE) {
            return -1;
        }
        
        return smallestDistance;
    }

    // A simple, easy helper method to flip the digits of a number
    // We keep this "private" so it acts purely as our internal kitchen tool!
    private int reverseNumber(int numberToReverse) {
        int flippedResult = 0;
        
        while (numberToReverse > 0) {
            // Grab the last digit (e.g., 123 % 10 = 3)
            int lastDigit = numberToReverse % 10;
            
            // Slide the current result to the left and add the new digit
            flippedResult = (flippedResult * 10) + lastDigit;
            
            // Chop off the last digit from the original number (e.g., 123 / 10 = 12)
            numberToReverse = numberToReverse / 10;
        }
        
        return flippedResult;
    }
}