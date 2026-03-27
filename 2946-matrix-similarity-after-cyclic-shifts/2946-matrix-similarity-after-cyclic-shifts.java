class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Normalize k to prevent unnecessary rotations
        k = k % n;
        
        // If k is 0, no effective shifts are made, so it's always true
        if (k == 0) {
            return true;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i % 2 == 0) {
                    // Even rows: check against the element shifted left
                    if (mat[i][j] != mat[i][(j + k) % n]) {
                        return false;
                    }
                } else {
                    // Odd rows: check against the element shifted right
                    if (mat[i][j] != mat[i][(j - k + n) % n]) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}