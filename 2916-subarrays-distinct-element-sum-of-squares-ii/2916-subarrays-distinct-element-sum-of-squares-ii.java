class Solution {
    static final int MOD = 1_000_000_007;

    long[] sumTree;
    long[] linTree;
    long[] lazy;

    void build(int node, int l, int r) {
        if (l == r) {
            sumTree[node] = 0;
            linTree[node] = 0;
            lazy[node] = 0;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        sumTree[node] = 0;
        linTree[node] = 0;
        lazy[node] = 0;
    }

    void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        sumTree[node] = (sumTree[node] + 2 * val * linTree[node] + (val * val % MOD) * len) % MOD;
        linTree[node] = (linTree[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        update(2 * node, l, mid, ql, qr, val);
        update(2 * node + 1, mid + 1, r, ql, qr, val);
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
        linTree[node] = (linTree[2 * node] + linTree[2 * node + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumTree = new long[4 * n];
        linTree = new long[4 * n];
        lazy = new long[4 * n];

        build(1, 0, n - 1);

        java.util.Map<Integer, Integer> lastPos = new java.util.HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int last = lastPos.getOrDefault(nums[j], -1);
            update(1, 0, n - 1, last + 1, j, 1);
            lastPos.put(nums[j], j);

            totalSum = (totalSum + sumTree[1]) % MOD;
        }

        return (int) totalSum;
    }
}