class Solution {
    public boolean isGood(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        // A good array must have exactly max + 1 elements
        if (nums.length != max + 1) {
            return false;
        }

        // Count frequencies of numbers
        int[] counts = new int[max + 1];
        for (int num : nums) {
            counts[num]++;
        }

        // Check if 1 to max-1 appear exactly once, and max appears exactly twice
        for (int i = 1; i < max; i++) {
            if (counts[i] != 1) return false;
        }
        if (counts[max] != 2) return false;

        return true;
    }
}
