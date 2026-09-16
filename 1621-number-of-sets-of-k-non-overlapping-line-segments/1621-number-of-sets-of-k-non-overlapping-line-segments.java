class Solution {
    public int numberOfSets(int n, int k) {
        int totalPoints = n + k - 1;
        int choices = 2 * k;
        
        long[][] combination = new long[totalPoints + 1][choices + 1];
        int modulo = 1_000_000_007;

        for (int i = 0; i <= totalPoints; i++) {
            combination[i][0] = 1;
            for (int j = 1; j <= Math.min(i, choices); j++) {
                combination[i][j] = (combination[i - 1][j - 1] + combination[i - 1][j]) % modulo;
            }
        }

        return (int) combination[totalPoints][choices];
    }
}
/*
Time Complexity: O((n + k) * k)
Space Complexity: O((n + k) * k)
*/
