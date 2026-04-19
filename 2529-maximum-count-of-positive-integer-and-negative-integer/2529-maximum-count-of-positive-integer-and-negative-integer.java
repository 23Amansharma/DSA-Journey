class Solution {
    public int maximumCount(int[] nums) {
    int count =0,pos=0;
    for(int i = 0;i<nums.length;i++){
        if(nums[i] < 0) 
        count ++;
        else if (nums[i]>0) pos++;
    } return Math.max(count,pos);

    }
}