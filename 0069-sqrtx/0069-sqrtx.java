class Solution {
    public int mySqrt(int x) {
        int store = 0;
        for(int j = 0;j<= x;j++)
        {
            if((long)j*j>x) break;
            store = j;
        }
        return store;
    }
}