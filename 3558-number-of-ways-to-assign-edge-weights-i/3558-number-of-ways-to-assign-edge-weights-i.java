class Solution {
    private List<List<Integer>> adj;
    private int maxDepth = 0;
    private static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Start DFS from root (node 1) with depth 0
        dfs(1, -1, 0);

        // Result is 2^(maxDepth - 1) % MOD
        return power(2, maxDepth - 1);
    }

    private void dfs(int u, int p, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, depth + 1);
            }
        }
    }

    private int power(long base, int exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return (int) res;
    }
}