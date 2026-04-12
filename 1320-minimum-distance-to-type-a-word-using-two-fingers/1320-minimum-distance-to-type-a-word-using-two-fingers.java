class Solution {
    public int minimumDistance(String word) {
        // dp[c] = minimum cost given the "other" finger is resting on character 'c'
        // Index 26 represents the state where a finger hasn't been placed yet.
        int[] dp = new int[27];
        
        // Initialize DP array with a very large number
        for (int i = 0; i <= 26; i++) {
            dp[i] = 30000; 
        }
        
        // Base case: after typing the first letter, one finger is on word[0], 
        // and the other finger is still floating (index 26) with 0 cost.
        dp[26] = 0;
        
        int n = word.length();
        for (int i = 1; i < n; i++) {
            int prev = word.charAt(i - 1) - 'A';
            int curr = word.charAt(i) - 'A';
            
            int[] nextDp = new int[27];
            for (int j = 0; j <= 26; j++) {
                nextDp[j] = 30000;
            }
            
            for (int other = 0; other <= 26; other++) {
                if (dp[other] != 30000) {
                    // Option 1: The finger at 'prev' moves to 'curr'.
                    // The 'other' finger stays at 'other'.
                    nextDp[other] = Math.min(nextDp[other], dp[other] + dist(prev, curr));
                    
                    // Option 2: The 'other' finger moves to 'curr'.
                    // The finger at 'prev' is left behind and becomes the new 'other' finger.
                    nextDp[prev] = Math.min(nextDp[prev], dp[other] + dist(other, curr));
                }
            }
            // Move to the next state
            dp = nextDp;
        }
        
        // The answer is the minimum value across all possible resting places for the other finger
        int minDistance = 30000;
        for (int i = 0; i <= 26; i++) {
            minDistance = Math.min(minDistance, dp[i]);
        }
        
        return minDistance;
    }
    
    // Helper method to calculate Manhattan distance on the 6-column keyboard
    private int dist(int a, int b) {
        // If the finger hasn't been placed yet, it costs nothing to place it anywhere
        if (a == 26) return 0; 
        
        int rowA = a / 6, colA = a % 6;
        int rowB = b / 6, colB = b % 6;
        
        return Math.abs(rowA - rowB) + Math.abs(colA - colB);
    }
}