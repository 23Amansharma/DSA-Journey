class Solution {
    public int pivotInteger(int n) {
        int sum = 0;
        int i = 0;
        while(i<=n){
            sum = sum + i;
            i++;
        }
       int var = (int)Math.sqrt(sum);
        if(var * var == sum) return var;
        else return -1;
    }
}