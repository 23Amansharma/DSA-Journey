class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        // dp[i] stores the maximum jumps to reach index i
        int[] dp = new int[n];
        
        // Initialize with -1 to indicate that the index is not reachable
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }
        
        // Starting point
        dp[0] = 0;
        
        // Iterate through each index
        for (int i = 1; i < n; i++) {
            // Check all previous indices to see if we can jump to i
            for (int j = 0; j < i; j++) {
                // If j is reachable and jump condition is satisfied
                if (dp[j] != -1 && Math.abs(nums[i] - nums[j]) <= target) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return dp[n - 1];
    }
}