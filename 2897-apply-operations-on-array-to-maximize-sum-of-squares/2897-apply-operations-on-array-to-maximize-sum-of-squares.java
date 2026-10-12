class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long sum = 0;
        long modulo = (long) Math.pow(10,9) + 7;
    int[] Checkbit = new int[32];
    // 1s bit check
    for(int x : nums){ // n times traverse
     for(int i =0;i<32;i++){ // at 31 time travel from 0 
        if(( x & ( 1<<i)) != 0) Checkbit[i] ++;
    }     
        }
        int j = 0;
        while(j<k){
            int no = 0;
            for(int i = 0;i<=31;i++)
            {
                if(Checkbit[i] > 0)
                {
                no  |= (1 << i);
                Checkbit[i]--;
                }
            }
            sum = (sum + (long) no*no) % modulo;
            j++;
        }
        return (int) sum;
    }
}