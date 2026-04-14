import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Step 1: Sort robots and factories by their coordinates
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Step 2: Flatten the factories
        // Each unit of capacity becomes an individual factory slot
        List<Integer> factorySlots = new ArrayList<>();
        for (int[] f : factory) {
            // We only need to add up to 'robot.size()' slots per factory
            int limit = Math.min(f[1], robot.size()); 
            for (int i = 0; i < limit; i++) {
                factorySlots.add(f[0]);
            }
        }
        
        int n = robot.size();
        long[] dp = new long[n + 1];
        
        // Use a safely large number representing infinity to prevent addition overflow
        long INF = (long) 1e15; 
        Arrays.fill(dp, INF);
        
        // Base case: 0 cost to repair 0 robots
        dp[0] = 0; 
        
        // Step 3: DP Transitions
        for (int slot : factorySlots) {
            // Traverse backwards to process the 1D DP array strictly in-place
            for (int i = n; i >= 1; i--) {
                if (dp[i - 1] != INF) {
                    long cost = Math.abs((long) robot.get(i - 1) - slot);
                    dp[i] = Math.min(dp[i], dp[i - 1] + cost);
                }
            }
        }
        
        return dp[n];
    }
}