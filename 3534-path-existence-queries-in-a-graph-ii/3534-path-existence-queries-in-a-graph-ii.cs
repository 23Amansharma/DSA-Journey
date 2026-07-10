using System;
using System.Collections.Generic;

public class Solution {
    public int[] PathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Pairs of (value, original_index)
        int[][] sorted = new int[n][];
        for (int i = 0; i < n; i++) {
            sorted[i] = new int[] { nums[i], i };
        }
        
        // Sort pairs by value ascending
        Array.Sort(sorted, (a, b) => a[0].CompareTo(b[0]));
        
        // Track the first sorted position where each original node index lands
        int[] originalToSortedPos = new int[n];
        for (int i = 0; i < n; i++) {
            originalToSortedPos[sorted[i][1]] = i;
        }
        
        // Binary lifting table size setup (2^17 = 131,072 > 10^5)
        int LOG = 18;
        int[,] st = new int[n, LOG];
        
        // Step 1: Precompute the single farthest right jump (st[i, 0]) using two pointers
        int r = 0;
        for (int i = 0; i < n; i++) {
            r = Math.Max(r, i);
            while (r + 1 < n && sorted[r + 1][0] - sorted[i][0] <= maxDiff) {
                r++;
            }
            st[i, 0] = r;
        }
        
        // Step 2: Build out the remaining binary lifting exponential tiers
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                st[i, j] = st[st[i, j - 1], j - 1];
            }
        }
        
        // Step 3: Process the queries accurately using the lift array
        int[] answer = new int[queries.Length];
        for (int i = 0; i < queries.Length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            if (u == v) {
                answer[i] = 0;
                continue;
            }
            
            // Convert original indices into sorted space
            int a = originalToSortedPos[u];
            int b = originalToSortedPos[v];
            
            // Direction doesn't matter in unweighted graph; handle left-to-right
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            
            // Quick check: can we bridge the connection natively in 1 hop?
            if (sorted[b][0] - sorted[a][0] <= maxDiff) {
                answer[i] = 1;
                continue;
            }
            
            // Greedily lift as far right as possible while staying strictly left of 'b'
            int curr = a;
            int steps = 0;
            for (int j = LOG - 1; j >= 0; j--) {
                if (st[curr, j] < b) {
                    curr = st[curr, j];
                    steps += (1 << j);
                }
            }
            
            // Try one final step from the absolute furthest position found
            if (st[curr, 0] >= b) {
                answer[i] = steps + 1;
            } else {
                answer[i] = -1; // Gap too large to bridge, separate components
            }
        }
        
        return answer;
    }
}
