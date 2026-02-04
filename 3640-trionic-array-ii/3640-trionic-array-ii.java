import java.util.*;

class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return 0; // Impossible to satisfy l < p < q < r

        // 1. Calculate max sum of strict increasing subarray ending at i (Len >= 2)
        long[] maxEnd = new long[n];
        long[] currentIncSum = new long[n]; // helper to track sum of current increasing run
        Arrays.fill(maxEnd, Long.MIN_VALUE);
        
        currentIncSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                // We can extend the run. 
                // Option 1: Extend previous max sum (if positive)
                // Option 2: Just take previous element + current (minimum requirement)
                // Logic: greedy extend is always better or equal for the sum ending at i
                // unless the prefix was negative.
                long prevSum = Math.max(0, currentIncSum[i - 1]) + nums[i]; 
                
                // However, strictly for the logic of "current increasing run":
                if (currentIncSum[i - 1] > 0) {
                     currentIncSum[i] = currentIncSum[i - 1] + nums[i];
                } else {
                     currentIncSum[i] = nums[i];
                }

                // But maxEnd[i] MUST include nums[i-1]. 
                // So it is nums[i] + (max sum ending at i-1 with len>=1)
                // Let's reuse a simple DP: 
                // dp[i] = max sum increasing ending at i (len >= 1)
                // maxEnd[i] = dp[i-1] + nums[i]
            } else {
                currentIncSum[i] = nums[i]; // Reset run
            }
        }

        // Re-calculate strictly for "Len >= 2" logic
        long[] dp = new long[n]; // dp[i] = max sum strict increasing ending at i (len >= 1)
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = nums[i] + Math.max(0, dp[i - 1]);
                maxEnd[i] = nums[i] + dp[i - 1]; // Must connect to i-1
            } else {
                dp[i] = nums[i];
                maxEnd[i] = Long.MIN_VALUE; // Cannot end here with len >= 2
            }
        }

        // 2. Calculate max sum of strict increasing subarray starting at i (Len >= 2)
        long[] maxStart = new long[n];
        Arrays.fill(maxStart, Long.MIN_VALUE);
        long[] dpRev = new long[n];
        dpRev[n - 1] = nums[n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                dpRev[i] = nums[i] + Math.max(0, dpRev[i + 1]);
                maxStart[i] = nums[i] + dpRev[i + 1]; // Must connect to i+1
            } else {
                dpRev[i] = nums[i];
                maxStart[i] = Long.MIN_VALUE; // Cannot start here with len >= 2
            }
        }

        long globalMax = Long.MIN_VALUE;

        // 3. Find Decreasing Segments (Peak p -> Valley q)
        int i = 0;
        while (i < n - 1) {
            if (nums[i] > nums[i + 1]) {
                // Found start of a decreasing sequence
                int p = i;
                long decSum = nums[p];
                int j = i + 1;
                
                // Follow the slope down
                while (j < n && nums[j] < nums[j - 1]) {
                    decSum += nums[j];
                    int q = j;
                    
                    // Check if this (p, q) pair is valid
                    // 1. p must be a valid end of an increasing seq (maxEnd[p] exists)
                    // 2. q must be a valid start of an increasing seq (maxStart[q] exists)
                    if (maxEnd[p] != Long.MIN_VALUE && maxStart[q] != Long.MIN_VALUE) {
                        // Formula: 
                        // Left Segment (ending at p) 
                        // + Middle Segment (p...q, excluding p and q to avoid double add)
                        // + Right Segment (starting at q)
                        
                        // Wait, maxEnd[p] includes nums[p]. maxStart[q] includes nums[q].
                        // decSum includes nums[p]...nums[q].
                        // Total = maxEnd[p] + maxStart[q] + (decSum - nums[p] - nums[q])
                        
                        long currentTotal = maxEnd[p] + maxStart[q] + (decSum - nums[p] - nums[q]);
                        if (currentTotal > globalMax) {
                            globalMax = currentTotal;
                        }
                    }
                    j++;
                }
                // Optimization: We can jump to j-1 (the valley)
                // because a valley cannot be a peak for the next run immediately
                // (unless it's a V shape, which is handled by outer loop increment)
                i = j - 1; 
            } else {
                i++;
            }
        }
        
        return globalMax;
    }
}