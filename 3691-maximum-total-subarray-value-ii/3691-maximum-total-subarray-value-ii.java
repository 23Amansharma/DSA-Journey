class Solution {
    // Sparse Table for RMQ (Range Max/Min Query)
    private int[][] maxST, minST;
    private int[] log;

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int maxLog = 31 - Integer.numberOfLeadingZeros(n);
        
        // Precompute Sparse Tables for O(1) RMQ
        maxST = new int[maxLog + 1][n];
        minST = new int[maxLog + 1][n];
        log = new int[n + 1];
        
        for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;
        for (int i = 0; i < n; i++) {
            maxST[0][i] = nums[i];
            minST[0][i] = nums[i];
        }
        
        for (int j = 1; j <= maxLog; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                maxST[j][i] = Math.max(maxST[j - 1][i], maxST[j - 1][i + (1 << (j - 1))]);
                minST[j][i] = Math.min(minST[j - 1][i], minST[j - 1][i + (1 << (j - 1))]);
            }
        }

        // greedy Approach using Max-Heap
        // PriorityQueue stores: {value, l, r}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
        
        for (int i = 0; i < n; i++) {
            pq.offer(new long[] { getVal(i, n - 1), i, n - 1 });
        }

        long totalValue = 0;
        for (int i = 0; i < k; i++) {
            long[] top = pq.poll();
            totalValue += top[0];
            int l = (int) top[1];
            int r = (int) top[2];
            
            // Push the next potential best (decrement r)
            if (r > l) {
                pq.offer(new long[] { getVal(l, r - 1), l, r - 1 });
            }
        }
        
        return totalValue;
    }

    private long getVal(int l, int r) {
        int j = log[r - l + 1];
        int maxVal = Math.max(maxST[j][l], maxST[j][r - (1 << j) + 1]);
        int minVal = Math.min(minST[j][l], minST[j][r - (1 << j) + 1]);
        return (long) maxVal - minVal;
    }
}