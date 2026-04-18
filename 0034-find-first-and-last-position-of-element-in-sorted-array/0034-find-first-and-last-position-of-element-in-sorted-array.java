class Solution {
    public int[] searchRange(int[] nums, int target) {
        int low = 0,high = nums.length-1,index1=-1,index2=-1;
        // we also creat array if not add in return position
        // int arr[] = new int[2];
        while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid] < target) low = mid +1;
            else if(nums[mid] > target) high = mid-1;
            else{
                index1 = mid;
                high = mid -1;
            }
            
        }
        low = 0;
        high = nums.length-1;
         while(low<=high){
            int mid = (low+high)/2;
            if(nums[mid] < target) low = mid +1;
            else if(nums[mid] > target) high = mid-1;
            else{
                index2 = mid;
                low = mid +1;
            }
            
        }
        return  new int[]{index1,index2};
    }
}