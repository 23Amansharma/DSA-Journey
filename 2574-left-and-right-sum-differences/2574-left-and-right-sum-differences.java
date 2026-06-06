class Solution {
    public int[] leftRightDifference(int[] nums) {
        int tsum = 0;
        for (int k : nums) {
            tsum += k ;
        }
        int[] ans = new int[nums.length];
        int rsum,lsum = 0;
        int i = 0;
        while(i<nums.length)
        {
            rsum = tsum - lsum - nums[i];
            ans[i] = Math.abs(lsum - rsum);
            lsum += nums[i];
            i++;
        }
        return ans;
    }
}