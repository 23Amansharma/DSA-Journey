import java.util.*;

class Solution {
    static class BIT {
        int[] tree;
        int n;
        BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }
        void update(int i, int val) {
            while (i <= n) {
                tree[i] = Math.max(tree[i], val);
                i += i & (-i);
            }
        }
        int query(int i) {
            int maxVal = 0;
            while (i > 0) {
                maxVal = Math.max(maxVal, tree[i]);
                i -= i & (-i);
            }
            return maxVal;
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int maxX = 0;
        for (int[] q : queries) maxX = Math.max(maxX, q[1]);
        int N = Math.max(maxX, 50005);

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(N);

        for (int[] q : queries) {
            if (q[0] == 1) obstacles.add(q[1]);
        }

        BIT bit = new BIT(N);
        int prev = 0;
        for (int curr : obstacles) {
            if (curr != 0) {
                bit.update(curr, curr - prev);
                prev = curr;
            }
        }

        List<Boolean> ans = new ArrayList<>();

        for (int i = queries.length - 1; i >= 0; i--) {
            int type = queries[i][0];
            int x = queries[i][1];

            if (type == 1) {
                obstacles.remove(x);
                int nextObstacle = obstacles.higher(x);
                int prevObstacle = obstacles.lower(x);
                bit.update(nextObstacle, nextObstacle - prevObstacle);
            } else {
                int sz = queries[i][2];
                int prevObstacle = obstacles.floor(x);
                int maxGapBefore = bit.query(prevObstacle);
                int lastGap = x - prevObstacle;

                if (maxGapBefore >= sz || lastGap >= sz) ans.add(true);
                else ans.add(false);
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}
