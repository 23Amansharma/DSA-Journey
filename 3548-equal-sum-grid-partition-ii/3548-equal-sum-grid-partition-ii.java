import java.util.Arrays;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MAX_VAL = 100005; // To safely store constraints up to 10^5
        
        long[] rowSum = new long[m];
        long[] colSum = new long[n];
        long totalSum = 0;
        
        // Arrays to store the boundaries of where each value exists in the grid
        int[] minRow = new int[MAX_VAL];
        int[] maxRow = new int[MAX_VAL];
        int[] minCol = new int[MAX_VAL];
        int[] maxCol = new int[MAX_VAL];
        
        Arrays.fill(minRow, Integer.MAX_VALUE);
        Arrays.fill(minCol, Integer.MAX_VALUE);
        Arrays.fill(maxRow, -1);
        Arrays.fill(maxCol, -1);
        
        // Step 1: Precompute Sums and Value Boundaries
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int val = grid[r][c];
                rowSum[r] += val;
                colSum[c] += val;
                totalSum += val;
                
                if (r < minRow[val]) minRow[val] = r;
                if (r > maxRow[val]) maxRow[val] = r;
                if (c < minCol[val]) minCol[val] = c;
                if (c > maxCol[val]) maxCol[val] = c;
            }
        }
        
        // Step 2: Check Horizontal Cuts
        long s1 = 0;
        for (int i = 0; i < m - 1; i++) {
            s1 += rowSum[i];
            long s2 = totalSum - s1;
            
            if (s1 == s2) return true;
            
            if (s1 > s2) {
                long diff = s1 - s2;
                if (diff > 0 && diff < MAX_VAL && checkTop(i, (int)diff, m, n, grid, minRow)) return true;
            } else {
                long diff = s2 - s1;
                if (diff > 0 && diff < MAX_VAL && checkBottom(i, (int)diff, m, n, grid, maxRow)) return true;
            }
        }
        
        // Step 3: Check Vertical Cuts
        s1 = 0;
        for (int j = 0; j < n - 1; j++) {
            s1 += colSum[j];
            long s2 = totalSum - s1;
            
            if (s1 == s2) return true;
            
            if (s1 > s2) {
                long diff = s1 - s2;
                if (diff > 0 && diff < MAX_VAL && checkLeft(j, (int)diff, m, n, grid, minCol)) return true;
            } else {
                long diff = s2 - s1;
                if (diff > 0 && diff < MAX_VAL && checkRight(j, (int)diff, m, n, grid, maxCol)) return true;
            }
        }
        
        return false;
    }
    
    // --- Helper Functions to check Connectivity Rules in O(1) Time ---
    
    private boolean checkTop(int i, int diff, int m, int n, int[][] grid, int[] minRow) {
        int R = i + 1, C = n;
        if (R > 1 && C > 1) return minRow[diff] <= i; // 2D Block
        else if (R == 1 && C > 1) return grid[0][0] == diff || grid[0][n - 1] == diff; // 1D Row
        else if (R > 1 && C == 1) return grid[0][0] == diff || grid[i][0] == diff; // 1D Col
        else return grid[0][0] == diff; // Single Cell
    }

    private boolean checkBottom(int i, int diff, int m, int n, int[][] grid, int[] maxRow) {
        int R = m - 1 - i, C = n;
        if (R > 1 && C > 1) return maxRow[diff] >= i + 1;
        else if (R == 1 && C > 1) return grid[i + 1][0] == diff || grid[i + 1][n - 1] == diff;
        else if (R > 1 && C == 1) return grid[i + 1][0] == diff || grid[m - 1][0] == diff;
        else return grid[i + 1][0] == diff;
    }

    private boolean checkLeft(int j, int diff, int m, int n, int[][] grid, int[] minCol) {
        int R = m, C = j + 1;
        if (R > 1 && C > 1) return minCol[diff] <= j;
        else if (R == 1 && C > 1) return grid[0][0] == diff || grid[0][j] == diff;
        else if (R > 1 && C == 1) return grid[0][0] == diff || grid[m - 1][0] == diff;
        else return grid[0][0] == diff;
    }

    private boolean checkRight(int j, int diff, int m, int n, int[][] grid, int[] maxCol) {
        int R = m, C = n - 1 - j;
        if (R > 1 && C > 1) return maxCol[diff] >= j + 1;
        else if (R == 1 && C > 1) return grid[0][j + 1] == diff || grid[0][n - 1] == diff;
        else if (R > 1 && C == 1) return grid[0][j + 1] == diff || grid[m - 1][j + 1] == diff;
        else return grid[0][j + 1] == diff;
    }
}