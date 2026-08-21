import java.util.Arrays;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Sort coins to potentially optimize LCM steps or early exits
        Arrays.sort(coins);
        
        long low = 1;
        long high = (long) coins[0] * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (countMultiples(mid, coins) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;  // Look for a larger amount
            }
        }
        return ans;
    }

    // Counts unique multiples <= mid using Inclusion-Exclusion
    private long countMultiples(long mid, int[] coins) {
        long count = 0;
        int n = coins.length;
        int totalSubsets = 1 << n; // 2^n subsets

        // Iterate through all non-empty subsets of coins
        for (int i = 1; i < totalSubsets; i++) {
            long currentLcm = 1;
            int elementsInSubset = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    elementsInSubset++;
                    currentLcm = lcm(currentLcm, coins[j]);
                    // If LCM exceeds mid, its contribution to mid/currentLcm becomes 0
                    if (currentLcm > mid) {
                        break;
                    }
                }
            }

            // Odd number of elements: add, Even number of elements: subtract
            if (elementsInSubset % 2 == 1) {
                count += mid / currentLcm;
            } else {
                count -= mid / currentLcm;
            }
        }
        return count;
    }

    // Helper method to find GCD
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper method to find LCM
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
