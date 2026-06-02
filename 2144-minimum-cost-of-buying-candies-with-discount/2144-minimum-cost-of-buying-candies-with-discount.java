class Solution {
    public int minimumCost(int[] cost) {
        int add = 0;
        Arrays.sort(cost);
     for(int i = cost.length - 1;i >= 0;i -= 3)
     {
       add += cost[i];
        if(i-1 >= 0) add += cost[i-1];
        if(cost[i] == 0) add  = cost[i];
     }
     return add;   
    }
}