class Solution {
    int[] parent;

    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        for (int i = 0; i < n; i++) {
            if (lcp[i][i] != n - i) return "";
        }

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    union(i, j);
                }
            }
        }

        char[] res = new char[n];
        java.util.Map<Integer, Character> map = new java.util.HashMap<>();
        char ch = 'a';

        for (int i = 0; i < n; i++) {
            int p = find(i);

            if (!map.containsKey(p)) {
                if (ch > 'z') return "";
                map.put(p, ch++);
            }

            res[i] = map.get(p);
        }

        String word = new String(res);

        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word.charAt(i) == word.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = 0;
                }

                if (dp[i][j] != lcp[i][j]) return "";
            }
        }

        return word;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) parent[pb] = pa;
    }
}