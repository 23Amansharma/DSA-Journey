import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        int[][] res = new int[m + 2][2];
        
        res[0] = new int[]{1, 0};         
        res[m + 1] = new int[]{n, n - 1};
        
        for (int i = 0; i < m; i++) {
            res[i + 1] = restrictions[i];
        }
        
        Arrays.sort(res, (a, b) -> Integer.compare(a[0], b[0]));
        
        int totalPoints = res.length;
        
       
        for (int i = 1; i < totalPoints; i++) {
            int idDiff = res[i][0] - res[i - 1][0];
            res[i][1] = Math.min(res[i][1], res[i - 1][1] + idDiff);
        }
        
        for (int i = totalPoints - 2; i >= 0; i--) {
            int idDiff = res[i + 1][0] - res[i][0];
            res[i][1] = Math.min(res[i][1], res[i + 1][1] + idDiff);
        }
        
        int maxTallest = 0;
        
        for (int i = 0; i < totalPoints - 1; i++) {
            int id1 = res[i][0], h1 = res[i][1];
            int id2 = res[i + 1][0], h2 = res[i + 1][1];
            int localPeak = (h1 + h2 + (id2 - id1)) / 2;
            maxTallest = Math.max(maxTallest, localPeak);
        }
        
        return maxTallest;
    }
}