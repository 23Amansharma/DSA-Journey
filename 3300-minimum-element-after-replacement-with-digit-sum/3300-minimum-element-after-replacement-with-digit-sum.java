class Solution {
    public int minElement(int[] nums) {
     int arr[] = new int[nums.length];
     for(int i = 0;i<nums.length;i++){
        int store=0;
        while(nums[i]>0){
        store = store + nums[i] % 10;
        nums[i] = nums[i]/10;
        }
        arr[i] = store;
        }
        int min = arr[0];
      for(int i = 1;i<arr.length;i++){
        if(arr[i]<min)  min = arr[i];
        }
        return min;
      }
}