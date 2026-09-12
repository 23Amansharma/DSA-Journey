import java.util.*;

class Solution {
    private static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    private static class Result {
        long weight;
        List<Integer> indices;

        Result(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] sorted = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sorted[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        // Sort intervals by start time
        Arrays.sort(sorted, (a, b) -> Integer.compare(a.l, b.l));

        // Precompute next non-overlapping interval index for each interval
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int target = sorted[i].r;
            int low = i + 1, high = n, ans = n;
            while (low < high) {
                int mid = (low + high) >>> 1;
                if (sorted[mid].l > target) {
                    ans = mid;
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            next[i] = ans;
        }

        // dp[i][count] stores the best Result starting from index i with 'count' picks remaining
        Result[][] dp = new Result[n + 1][5];

        for (int count = 0; count <= 4; count++) {
            dp[n][count] = new Result(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int count = 0; count <= 4; count++) {
                // Base case: 0 choices left
                if (count == 0) {
                    dp[i][count] = new Result(0, new ArrayList<>());
                    continue;
                }

                // Option 1: Skip current interval
                Result skip = dp[i + 1][count];

                // Option 2: Pick current interval
                Result takeNext = dp[next[i]][count - 1];
                long takeWeight = sorted[i].weight + takeNext.weight;
                List<Integer> takeIndices = new ArrayList<>();
                takeIndices.add(sorted[i].id);
                takeIndices.addAll(takeNext.indices);
                Collections.sort(takeIndices);

                Result take = new Result(takeWeight, takeIndices);

                // Compare Skip vs Take
                dp[i][count] = compare(skip, take);
            }
        }

        List<Integer> bestIndices = dp[0][4].indices;
        int[] res = new int[bestIndices.size()];
        for (int i = 0; i < bestIndices.size(); i++) {
            res[i] = bestIndices.get(i);
        }
        return res;
    }

    private Result compare(Result r1, Result r2) {
        if (r1.weight > r2.weight) return r1;
        if (r2.weight > r1.weight) return r2;

        // Weights are equal, choose lexicographically smaller sequence
        int len = Math.min(r1.indices.size(), r2.indices.size());
        for (int i = 0; i < len; i++) {
            int cmp = Integer.compare(r1.indices.get(i), r2.indices.get(i));
            if (cmp < 0) return r1;
            if (cmp > 0) return r2;
        }
        return r1.indices.size() <= r2.indices.size() ? r1 : r2;
    }
}