class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int tsum = 0;
        for (int k : nums) {
            tsum += k ;
        }
        int[] ans = new int[n];
        int lsum = 0;
        int rsum;
        for (int i = 0; i < n; i++) {
            rsum = tsum - lsum - nums[i];
            ans[i] = Math.abs(lsum - rsum);
            lsum += nums[i];
        }
        return ans;
    }
}