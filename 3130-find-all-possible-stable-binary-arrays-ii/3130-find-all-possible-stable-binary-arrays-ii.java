class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        
        // dp[zeros][ones][lastDigit] (0 ya 1)
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Base cases: Sirf 0 ya sirf 1 wali valid series set karo
        for (int i = 1; i <= Math.min(zero, limit); i++) dp[i][0][0] = 1; 
        for (int j = 1; j <= Math.min(one, limit); j++) dp[0][j][1] = 1;

        // DP table fill karna
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                
                // Naya '0' append karo
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                // Agar '0' limit cross kare, toh invalid (- limit - 1) subtract karo
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }

                // Naya '1' append karo
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                // Agar '1' limit cross kare, toh invalid subtract karo
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }

        // Final ans: 0 pe end hone wale + 1 pe end hone wale
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}