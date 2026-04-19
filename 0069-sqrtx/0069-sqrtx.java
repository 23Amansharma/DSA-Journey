class Solution {
    public int mySqrt(int x) {
        int low = 0,high = x,store = 0;
        while(low<=high){
            int mid = (low+high)/2;
            if((long)mid*mid<=x){
         store = mid;
         low = mid+1;}
         else high = mid-1;
        }
    return store;
    }
}