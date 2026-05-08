import java.util.*;

class Solution {

    static final int MAX = 1_000_000;
    static int[] spf = buildSPF();

    private static int[] buildSPF() {
        int[] spf = new int[MAX + 1];

        for (int i = 0; i <= MAX; i++) {
            spf[i] = i;
        }

        for (int i = 2; i * i <= MAX; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        return spf;
    }

    public int minJumps(int[] nums) {

        int n = nums.length;
        if (n == 1) return 0;

        // prime -> indices divisible by prime
        List<Integer>[] pos = new ArrayList[MAX + 1];

        // Build mapping
        for (int i = 0; i < n; i++) {

            int x = nums[i];

            while (x > 1) {
                int p = spf[x];

                if (pos[p] == null) {
                    pos[p] = new ArrayList<>();
                }

                pos[p].add(i);

                // remove duplicate prime factors
                while (x % p == 0) {
                    x /= p;
                }
            }
        }

        boolean[] vis = new boolean[n];
        boolean[] usedPrime = new boolean[MAX + 1];

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        vis[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int i = q.poll();

                if (i == n - 1) return steps;

                // left
                if (i > 0 && !vis[i - 1]) {
                    vis[i - 1] = true;
                    q.offer(i - 1);
                }

                // right
                if (i + 1 < n && !vis[i + 1]) {
                    vis[i + 1] = true;
                    q.offer(i + 1);
                }

                int val = nums[i];

                // teleport only if current value itself is prime
                if (val > 1 && spf[val] == val && !usedPrime[val]) {

                    usedPrime[val] = true;

                    List<Integer> list = pos[val];

                    if (list != null) {

                        for (int idx : list) {

                            if (!vis[idx]) {
                                vis[idx] = true;
                                q.offer(idx);
                            }
                        }

                        // IMPORTANT OPTIMIZATION
                        pos[val] = null;
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}