class Solution {
    Long[][][][][][] memo;
    String S;

    public long totalWaviness(long num1, long num2) {
        return countWaviness(num2) - countWaviness(num1 - 1);
    }

    private long countWaviness(long N) {
        if (N < 100) return 0;
        S = String.valueOf(N);
        // Initialize memoization table with dimensions based on constraints
        memo = new Long[S.length()][2][2][11][11][S.length()];
        return dp(0, true, false, 10, 10, 0);
    }

    private long dp(int idx, boolean isLess, boolean isStarted, int p1, int p2, int count) {
        if (idx == S.length()) return count;
        if (memo[idx][isLess ? 1 : 0][isStarted ? 1 : 0][p1][p2][count] != null) 
            return memo[idx][isLess ? 1 : 0][isStarted ? 1 : 0][p1][p2][count];

        long total = 0;
        int limit = isLess ? (S.charAt(idx) - '0') : 9;

        for (int d = 0; d <= limit; d++) {
            boolean nextIsLess = isLess && (d == limit);
            if (!isStarted) {
                total += dp(idx + 1, nextIsLess, d > 0, d > 0 ? d : 10, 10, 0);
            } else {
                int newCount = count;
                // Check peak/valley using p1 (prev1) and p2 (prev2)
                if (p2 != 10) { // Can only form peak/valley if we have 3 digits
                    if ((p1 > p2 && p1 > d) || (p1 < p2 && p1 < d)) {
                        newCount++;
                    }
                }
                total += dp(idx + 1, nextIsLess, true, d, p1, newCount);
            }
        }
        return memo[idx][isLess ? 1 : 0][isStarted ? 1 : 0][p1][p2][count] = total;
    }
}