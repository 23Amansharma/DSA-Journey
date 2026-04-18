class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int low = 0,high = arr.length-1;
        while(low<high){
            int mid = (low+high)/2;
            if(arr[mid]<arr[mid+1]) low = mid + 1;
            else high = mid;// arr[mid]>arr[mid+1]
        }
        return high;//low == high dono pointer same index pr aane pr equal 
    }
}