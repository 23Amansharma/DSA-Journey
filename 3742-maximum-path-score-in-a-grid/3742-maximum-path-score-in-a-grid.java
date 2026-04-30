class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        int[][][] dp = new int[m][n][k + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    dp[i][j][c] = -1;
                }
            }
        }

        dp[0][0][0] = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int val = grid[i][j];
                int cost = (val == 0) ? 0 : 1;
                int score = val;

                for (int c = 0; c <= k; c++) {

                    if (i > 0 && dp[i - 1][j][c] != -1) {
                        int newCost = c + cost;
                        if (newCost <= k) {
                            dp[i][j][newCost] = Math.max(
                                dp[i][j][newCost],
                                dp[i - 1][j][c] + score
                            );
                        }
                    }

                    if (j > 0 && dp[i][j - 1][c] != -1) {
                        int newCost = c + cost;
                        if (newCost <= k) {
                            dp[i][j][newCost] = Math.max(
                                dp[i][j][newCost],
                                dp[i][j - 1][c] + score
                            );
                        }
                    }
                }
            }
        }

        int ans = -1;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[m - 1][n - 1][c]);
        }

        return ans;
    }
}