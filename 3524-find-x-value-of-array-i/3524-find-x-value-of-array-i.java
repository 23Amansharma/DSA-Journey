class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];
        
        for (int x : nums) {
            long[] newDp = new long[k];
            int var = x % k;
            
            newDp[var] += 1;
            
            for (int i = 0; i < k; i++) {
                if (dp[i] > 0) {
                    int next = (i * var) % k;
                    newDp[next] += dp[i];
                }
            }
            
            for (int i = 0; i < k; i++) {
                ans[i] += newDp[i];
            }
            
            dp = newDp;
        }
        
        return ans;
    }
}
