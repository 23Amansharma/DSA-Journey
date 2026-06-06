class Solution {
    public int[] leftRightDifference(int[] nums) {
        int tsum = 0;
        for (int k : nums) {
            tsum += k ;
        }
        int[] ans = new int[nums.length];
        int rsum,lsum = 0;
        for (int i = 0; i < nums.length; i++) {
            rsum = tsum - lsum - nums[i];
            ans[i] = Math.abs(lsum - rsum);
            lsum += nums[i];
        }
        return ans;
    }
}