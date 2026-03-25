class Solution {
    // Just changed the name here to match LeetCode's template: canPartitionGrid
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Use long to prevent Integer Overflow
        long[] rowSums = new long[m];
        long[] colSums = new long[n];
        long totalSum = 0;
        
        // Step 1: Calculate sum of each row, each column, and the total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                rowSums[i] += val;
                colSums[j] += val;
                totalSum += val;
            }
        }
        
        // If the total sum is odd, it cannot be divided into two equal integers
        if (totalSum % 2 != 0) {
            return false;
        }
        
        long target = totalSum / 2;
        
        // Step 2: Check for a valid Horizontal Cut
        long currentSum = 0;
        // Notice we loop until m - 1, because the second section must be non-empty
        for (int i = 0; i < m - 1; i++) {
            currentSum += rowSums[i];
            if (currentSum == target) {
                return true;
            }
        }
        
        // Step 3: Check for a valid Vertical Cut
        currentSum = 0;
        // Notice we loop until n - 1
        for (int j = 0; j < n - 1; j++) {
            currentSum += colSums[j];
            if (currentSum == target) {
                return true;
            }
        }
        
        // If no valid cut was found
        return false;
    }
}