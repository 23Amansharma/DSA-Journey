class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int smallsbscore;
        int[] arr = new int[n];
        
        arr[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            arr[i] = Math.min(nums[i], arr[i + 1]);
        }
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            int min = arr[i];
            smallsbscore = max - min;
            
            if (smallsbscore <= k) {
                return i;
            }
        }
        
        return -1;
    }
}