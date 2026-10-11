class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int x = 1;x<= n; x++){
            if( n  % x  == 0) {// special no condition base on index 
                sum = sum + (int) Math.pow(nums[x-1],2); // array start  0 base indexing so x is basically x-1
            }
        }
        return sum;
    }
}