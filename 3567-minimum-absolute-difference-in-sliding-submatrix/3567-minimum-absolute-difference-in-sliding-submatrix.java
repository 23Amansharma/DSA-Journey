import java.util.Arrays;

class Solution {
    // minAbsDiff
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Result matrix
        int[][] ans = new int[m - k + 1][n - k + 1];
        
        // Memory optimization: One temp array
        int[] temp = new int[k * k];
        
        // Submatrix ka top-left corner
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                
                //  Elements extract karna
                int idx = 0;
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        temp[idx++] = grid[x][y];
                    }
                }
                
                // Array ko sort karna
                Arrays.sort(temp);
                
                //  Minimum absolute difference nikalna (Distinct values ke beech)
                int minDiff = Integer.MAX_VALUE;
                for (int p = 1; p < temp.length; p++) {
                    if (temp[p] != temp[p - 1]) { // Distinct check
                        minDiff = Math.min(minDiff, temp[p] - temp[p - 1]);
                    }
                }
                
                // Agar sab elements same the, toh 0 daal do
                if (minDiff == Integer.MAX_VALUE) {
                    ans[i][j] = 0;
                } else {
                    ans[i][j] = minDiff;
                }
            }
        }
        
        return ans;
    }
}