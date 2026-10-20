class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        
        // Create a sorted reference array
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        // Find all valid divisors k of n
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }
        
        // Check each divisor k
        for (int x : divisors) {
            boolean isSortable = true;
            
            // Check each block of size x
            for (int i = 0; i < n; i += x) {
                if (!canRotateToMatch(nums, i, sortedNums, i, x)) {
                    isSortable = false;
                    break;
                }
            }
            
            if (isSortable) {
                totalSum += x;
            }
        }
        
        return totalSum;
    }
    
    // Helper function to check if the block can be cyclically rotated to match the target segment
    private boolean canRotateToMatch(int[] nums, int srcIdx, int[] sortedNums, int targetIdx, int k) {
        int firstVal = sortedNums[targetIdx];
        
        for (int i = 0; i < k; i++) {
            // Optimization: Skip duplicate values to prevent TLE when elements are identical
            if (i > 0 && nums[srcIdx + i] == nums[srcIdx + i - 1]) {
                continue;
            }
            
            if (nums[srcIdx + i] == firstVal) {
                boolean match = true;
                for (int j = 0; j < k; j++) {
                    if (nums[srcIdx + (i + j) % k] != sortedNums[targetIdx + j]) {
                        match = false;
                        break;
                    }
                }
                if (match) return true;
            }
        }
        
        return false;
    }
}