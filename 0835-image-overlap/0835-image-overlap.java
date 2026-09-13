import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    list1.add(i * 100 + j);
                }
                if (img2[i][j] == 1) {
                    list2.add(i * 100 + j);
                }
            }
        }
        
      
        Map<Integer, Integer> counts = new HashMap<>();
        int maxOverlap = 0;
        
        for (int p1 : list1) {
            for (int p2 : list2) {
                
                int diff = p1 - p2; 
                counts.put(diff, counts.getOrDefault(diff, 0) + 1);
                maxOverlap = Math.max(maxOverlap, counts.get(diff));
            }
        }
        
        return maxOverlap;
    }
}
