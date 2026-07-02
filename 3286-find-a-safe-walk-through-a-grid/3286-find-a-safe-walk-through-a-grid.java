import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        int[][] maxHealth = new int[m][n];
        for (int[] row : maxHealth) {
            Arrays.fill(row, -1);
        }
        
        Deque<int[]> deque = new ArrayDeque<>();
        
        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0) return false;
        
        maxHealth[0][0] = startHealth;
        deque.offerFirst(new int[]{0, 0, startHealth});
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int h = curr[2];
            
            if (h < maxHealth[r][c]) continue;
            
            if (r == m - 1 && c == n - 1 && h >= 1) {
                return true;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextHealth = h - grid.get(nr).get(nc);
                    
                    if (nextHealth > maxHealth[nr][nc]) {
                        maxHealth[nr][nc] = nextHealth;
                        
                        if (grid.get(nr).get(nc) == 0) {
                            deque.offerFirst(new int[]{nr, nc, nextHealth});
                        } else {
                            deque.offerLast(new int[]{nr, nc, nextHealth});
                        }
                    }
                }
            }
        }
        
        return false;
    }
}
