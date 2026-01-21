class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0) {
                zeroCount++;
            }

            // Agar window me 1 se zyada zero ho jaye
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // right - left = window size - 1 (kyunki ek delete karna hi hai)
            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }
}
