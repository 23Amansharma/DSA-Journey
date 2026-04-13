class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        
        // Scan outwards from distance 0 up to the maximum possible distance
        for (int i = 0; i < n; i++) {
            // Check the right side at distance 'i'
            if (start + i < n && nums[start + i] == target) {
                return i;
            }
            // Check the left side at distance 'i'
            if (start - i >= 0 && nums[start - i] == target) {
                return i;
            }
        }
        
        return 0; // Fallback (Constraints guarantee the target always exists)
    }
}