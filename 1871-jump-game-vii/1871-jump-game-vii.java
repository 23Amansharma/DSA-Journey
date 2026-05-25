class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        // If the last index is '1', we can't possibly reach it
        if (s.charAt(n - 1) == '1') {
            return false;
        }

        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        int reachableCount = 0;
        
        for (int i = 1; i < n; i++) {
            // Add the dp state that entered the window (i - minJump)
            if (i - minJump >= 0) {
                reachableCount += dp[i - minJump] ? 1 : 0;
            }
            
            // Remove the dp state that left the window (i - maxJump - 1)
            if (i - maxJump - 1 >= 0) {
                reachableCount -= dp[i - maxJump - 1] ? 1 : 0;
            }
            
            // A position is reachable if there is at least one previous 
            // reachable state within our jump range and the current char is '0'
            if (reachableCount > 0 && s.charAt(i) == '0') {
                dp[i] = true;
            }
        }
        
        return dp[n - 1];
    }
}
