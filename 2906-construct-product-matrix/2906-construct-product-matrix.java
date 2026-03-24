class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int mod = 12345;
        
        // This will be our final result matrix
        int[][] p = new int[n][m];
        
        // Pass 1: Calculate the Prefix Product
        long prefix = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) prefix;
                // Update prefix for the next cell and apply modulo immediately
                prefix = (prefix * grid[i][j]) % mod;
            }
        }
        
        // Pass 2: Calculate the Suffix Product and merge it
        long suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // Multiply the existing prefix product with the suffix product
                p[i][j] = (int) ((p[i][j] * suffix) % mod);
                // Update suffix for the previous cell and apply modulo
                suffix = (suffix * grid[i][j]) % mod;
            }
        }
        
        return p;
    }
}