class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1000000007; // Modulo value for the final answer
        
        // DP arrays to store max and min products at each cell
        long[][] maxDP = new long[m][n];
        long[][] minDP = new long[m][n];
        
        // Step 1: Base Case
        maxDP[0][0] = grid[0][0];
        minDP[0][0] = grid[0][0];
        
        // Step 2: Fill the first row
        for (int j = 1; j < n; j++) {
            maxDP[0][j] = maxDP[0][j-1] * grid[0][j];
            minDP[0][j] = minDP[0][j-1] * grid[0][j];
        }
        
        // Step 3: Fill the first column
        for (int i = 1; i < m; i++) {
            maxDP[i][0] = maxDP[i-1][0] * grid[i][0];
            minDP[i][0] = minDP[i-1][0] * grid[i][0];
        }
        
        // Step 4: The Main DP Loop
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                
                if (val >= 0) {
                    // Current value is positive or zero
                    maxDP[i][j] = Math.max(maxDP[i-1][j], maxDP[i][j-1]) * val;
                    minDP[i][j] = Math.min(minDP[i-1][j], minDP[i][j-1]) * val;
                } else {
                    // Current value is negative (Flip the max and min logic)
                    maxDP[i][j] = Math.min(minDP[i-1][j], minDP[i][j-1]) * val;
                    minDP[i][j] = Math.max(maxDP[i-1][j], maxDP[i][j-1]) * val;
                }
            }
        }
        
        // Step 5: Final Result Check and Modulo
        long ans = maxDP[m-1][n-1];
        
        if (ans < 0) {
            return -1;
        }
        
        return (int) (ans % MOD);
    }
}