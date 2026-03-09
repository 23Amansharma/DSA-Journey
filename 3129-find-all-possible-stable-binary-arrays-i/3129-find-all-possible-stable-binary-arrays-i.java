class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        
        // dp[zerosCount][onesCount][lastDigit]
        // lastDigit 0 ka matlab: array 0 pe end ho raha hai
        // lastDigit 1 ka matlab: array 1 pe end ho raha hai
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Base Case 1: Agar array me sirf '0' hain (ek bhi '1' nahi hai)
        // Ye tabhi tak valid hai jab tak '0' ka count limit cross na kare
        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1; 
        }

        // Base Case 2: Agar array me sirf '1' hain (ek bhi '0' nahi hai)
        // Ye tabhi tak valid hai jab tak '1' ka count limit cross na kare
        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }

        // DP table ko populate karna start karte hain
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                
                // --------------------------------------------------------
                // CASE A: Naya digit '0' append karna hai (ending with 0)
                // --------------------------------------------------------
                // Pichli saari valid series me naya '0' add kar do
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                
                // THE TRICK (Subtraction): Agar '0' ka count limit se zyada ho gaya
                // Toh un invalid arrays ko minus karo jahan exactly limit+1 baar lagataar '0' aaye the
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }

                // --------------------------------------------------------
                // CASE B: Naya digit '1' append karna hai (ending with 1)
                // --------------------------------------------------------
                // Pichli saari valid series me naya '1' add kar do
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                
                // THE TRICK (Subtraction): Agar '1' ka count limit se zyada ho gaya
                // Toh un invalid arrays ko minus karo jahan exactly limit+1 baar lagataar '1' aaye the
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }

        // Final Answer: Saari valid series jo 0 pe khatam hui + jo 1 pe khatam hui
        long totalStableArrays = (dp[zero][one][0] + dp[zero][one][1]) % MOD;
        
        return (int) totalStableArrays;
    }
}