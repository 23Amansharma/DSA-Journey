import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int left = 0;
        int ans = 0;
        
        for (int right = 0; right < nums.length; right++) {
            counts.put(nums[right], counts.getOrDefault(nums[right], 0) + 1);
            
            while (counts.get(nums[right]) > k) {
                counts.put(nums[left], counts.get(nums[left]) - 1);
                left++;
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}
