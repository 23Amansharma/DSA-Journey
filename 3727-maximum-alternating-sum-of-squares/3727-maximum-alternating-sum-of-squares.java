import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];
        for (int i = 0; i < nums.length; i++) squares[i] = (long) nums[i] * nums[i];
        Arrays.sort(squares);
        long sum = 0;
        int mid = nums.length / 2;
        for (int i = 0; i < mid; i++)
            sum -= squares[i];
        for (int i = mid; i < nums.length; i++)
            sum += squares[i];
        
        return sum;
    }
}