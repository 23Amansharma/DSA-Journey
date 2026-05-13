class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        // The maximum possible sum is 2 * limit, so we need a difference array of size 2*limit + 2
        int[] delta = new int[2 * limit + 2];
        
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            
            // For a pair (a, b), the possible sum S ranges from 2 to 2*limit.
            // 0 moves needed: sum is a+b
            // 1 move needed: sum range is [1 + min(a,b), limit + max(a,b)]
            // 2 moves needed: all other sums
            
            int minSum = Math.min(a, b) + 1;
            int maxSum = Math.max(a, b) + limit;
            int currentSum = a + b;
            
            // Default: 2 moves needed for all sums
            delta[2] += 2;
            delta[2 * limit + 1] -= 2;
            
            // 1 move needed for sums in [minSum, maxSum]
            delta[minSum] -= 1;
            delta[maxSum + 1] += 1;
            
            // 0 moves needed if sum is exactly currentSum
            delta[currentSum] -= 1;
            delta[currentSum + 1] += 1;
        }
        
        int minMoves = n;
        int currentMoves = 0;
        // Iterate through all possible sums to find the minimum moves
        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += delta[i];
            minMoves = Math.min(minMoves, currentMoves);
        }
        
        return minMoves;
    }
}
