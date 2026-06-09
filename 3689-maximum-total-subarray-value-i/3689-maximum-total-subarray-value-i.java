class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxVal = nums[0];
        int minVal = nums[0];
        // Puri array mein max aur min find kiya
        for (int num : nums) {
            if (num > maxVal) maxVal = num;
            if (num < minVal) minVal = num;
        }
        return (long) (maxVal - minVal) * k;
    }
}