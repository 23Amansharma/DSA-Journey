class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        long MOD = 1000000007L; // 10^9 + 7
    
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int k = queries[i][2];
            long v = queries[i][3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int) ((nums[idx] * v) % MOD);
            }
        }
        

        int finalXor = 0;
        for (int i = 0; i < nums.length; i++) {
            finalXor ^= nums[i];
        }
        
        return finalXor;
    }
}