class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int changeLastTwo = nums[n - 3] - nums[0];
        int changeFirstTwo = nums[n - 1] - nums[2];
        int changeOneEach = nums[n - 2] - nums[1];
        return Math.min(changeLastTwo, Math.min(changeFirstTwo, changeOneEach));
    }
}