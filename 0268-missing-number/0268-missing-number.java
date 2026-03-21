class Solution {
    public int missingNumber(int[] nums) {
        // 1. Array ka size nikal lo
        int n = nums.length;
        
        // 2. Expected sum nikalo (0 se n tak)
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        // 3. Array ke har number ko actualSum mein jodo
        for (int num : nums) {
            actualSum += num;
        }
        
        // 4. Dono ka difference return kar do (print nahi karna hai)
        return expectedSum - actualSum;
    }
}