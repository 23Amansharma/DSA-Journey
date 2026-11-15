class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int minSum = Integer.MAX_VALUE;
        int n = nums.size();
        
        // Starting index of subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            // Ending index of subarray
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                // Check if length is within [l, r]
                if (length >= l && length <= r) {
                    // Check if sum is positive (> 0)
                    if (currentSum > 0) {
                        minSum = Math.min(minSum, currentSum);
                    }
                }
                
                // Agar length r se badi ho gayi, toh aage badhne ki zaroorat nahi
                if (length > r) {
                    break;
                }
            }
        }
        
        // Agar koi valid subarray nahi mila toh -1 return
        return (minSum == Integer.MAX_VALUE) ? -1 : minSum;
    }
}