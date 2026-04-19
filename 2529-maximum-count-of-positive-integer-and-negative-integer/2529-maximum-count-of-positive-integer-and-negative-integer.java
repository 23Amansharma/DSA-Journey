class Solution {
    public int maximumCount(int[] nums) {
    int count =0,pos=0,i = 0;
    while(i<nums.length){
        if(nums[i] < 0) 
        count ++;
        else if (nums[i]>0) pos++;
        i++;
    } return Math.max(count,pos);

    }
}