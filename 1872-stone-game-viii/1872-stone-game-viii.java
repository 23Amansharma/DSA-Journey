class Solution {
    // Approach: Dynamic Programming / Game Theory
    // Technique: Prefix Sums / Backward Iteration / Space Optimization
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        int maxDifference = stones[n - 1];
        
        for (int i = n - 2; i > 0; i--) {
            maxDifference = Math.max(maxDifference, stones[i] - maxDifference);
        }
        
        return maxDifference;
    }
}
