class Solution {
    public int minMoves2(int[] nums) {
       int n = nums.length;
       Arrays.sort(nums);
       int mid = nums[n/2];
    // nm means no of moves
    long nm =0;
    for(int i = 0;i<n;i++){
nm += Math.abs((long)nums[i]-mid);
    }
       return (int)nm;
    }
}