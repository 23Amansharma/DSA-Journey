import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Direction vectors: North, East, South, West
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0; // Starts facing North (index 0)
        int x = 0, y = 0;
        int maxDistSq = 0;
        
        // Store obstacles in a HashSet for O(1) lookups
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obstacleSet.add(encode(obs[0], obs[1]));
        }
        
        for (int cmd : commands) {
            if (cmd == -2) {
                // Turn left (equivalent to +3 modulo 4)
                d = (d + 3) % 4;
            } else if (cmd == -1) {
                // Turn right (+1 modulo 4)
                d = (d + 1) % 4;
            } else {
                // Move forward step by step
                for (int i = 0; i < cmd; i++) {
                    int nextX = x + dirs[d][0];
                    int nextY = y + dirs[d][1];
                    
                    // Check if the next step is an obstacle
                    if (obstacleSet.contains(encode(nextX, nextY))) {
                        break; // Stop moving in this direction
                    }
                    
                    // Update position
                    x = nextX;
                    y = nextY;
                    
                    // Track maximum squared distance
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }
        
        return maxDistSq;
    }
   
    private long encode(int x, int y) {
        long ox = x + 30000;
        long oy = y + 30000;
        return (ox << 16) | oy; 
    }
}