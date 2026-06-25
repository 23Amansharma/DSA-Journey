class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int res = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int cnt = 0; 
            
            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    cnt++;
                }
                
                int len = j - i + 1;
                
                if (cnt * 2 > len) {
                    res++;
                }
            }
        }
        
        return res;
    }
}