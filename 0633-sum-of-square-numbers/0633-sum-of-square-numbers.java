class Solution {
    public boolean judgeSquareSum(int c) {
        int y;
        if( c == 0) return true;
        for( int x = 0;x<Math.sqrt(c);x++){
            int temp = c - x*x;
            y =  (int)Math.sqrt(temp);
            if(c ==  x*x + y*y)return true;
        }
        return false;
    }
}