class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        int maxPossibleStrength = 0;
        int mandatoryCount = 0;
        
        // Find exact bounds and array sizes
        for (int[] e : edges) {
            if (e[3] == 1) mandatoryCount++;
            int potential = (e[3] == 1) ? e[2] : e[2] * 2;
            if (potential > maxPossibleStrength) maxPossibleStrength = potential;
        }
        
        // Pre-partition to avoid conditional branching in the hot loop
        int[][] mandatory = new int[mandatoryCount][3];
        int[][] optional = new int[edges.length - mandatoryCount][3];
        int mIdx = 0, oIdx = 0;
        
        DSU initialDsu = new DSU(n);
        int minMandatory = Integer.MAX_VALUE;
        
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (!initialDsu.union(e[0], e[1])) return -1; // Cycle detected
                if (e[2] < minMandatory) minMandatory = e[2];
                mandatory[mIdx++] = new int[]{e[0], e[1], e[2]};
            } else {
                optional[oIdx++] = new int[]{e[0], e[1], e[2]};
            }
        }
        
        // Pre-allocate the worker DSU for zero-allocation looping
        DSU workerDsu = new DSU(n);
        if (!canAchieve(n, mandatory, optional, k, 0, workerDsu)) return -1;
        
        int left = 1;
        int right = (minMandatory == Integer.MAX_VALUE) ? maxPossibleStrength : Math.min(maxPossibleStrength, minMandatory);
        int maxStability = -1;
        
        // Binary Search Hot Loop
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canAchieve(n, mandatory, optional, k, mid, workerDsu)) {
                maxStability = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return maxStability;
    }
    
    private boolean canAchieve(int n, int[][] mandatory, int[][] optional, int k, int target, DSU dsu) {
        dsu.reset(); // $O(N)$ reset instead of $O(N)$ allocation
        int components = n;
        
        for (int[] e : mandatory) {
            if (e[2] < target) return false;
            if (dsu.union(e[0], e[1])) components--;
        }
        
        for (int[] e : optional) {
            if (e[2] >= target) {
                if (dsu.union(e[0], e[1])) components--;
            }
        }
        
        int upgrades = 0;
        for (int[] e : optional) {
            if (e[2] < target && e[2] * 2 >= target) {
                if (dsu.union(e[0], e[1])) {
                    components--;
                    upgrades++;
                }
            }
        }
        
        return components == 1 && upgrades <= k;
    }
    
    // Stack-safe, reusable DSU
    static class DSU {
        private final int[] parent;
        
        public DSU(int n) {
            parent = new int[n];
            reset();
        }
        
        public void reset() {
            for (int i = 0; i < parent.length; i++) parent[i] = i;
        }
        
        public int find(int i) {
            int root = i;
            while (root != parent[root]) root = parent[root];
            
            // Iterative path compression
            int curr = i;
            while (curr != root) {
                int nxt = parent[curr];
                parent[curr] = root;
                curr = nxt;
            }
            return root;
        }
        
        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }
    }
}