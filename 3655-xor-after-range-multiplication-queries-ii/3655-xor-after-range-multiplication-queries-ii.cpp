#include <vector>

using namespace std;

class Solution {
    long long power(long long base, long long exp) {
        long long res = 1;
        base %= 1000000007;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % 1000000007;
            base = (base * base) % 1000000007;
            exp /= 2;
        }
        return res;
    }

    // Function to calculate modular inverse using Fermat's Little Theorem
    long long modInverse(long long n) {
        return power(n, 1000000007 - 2);
    }

public:
    int xorAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        long long MOD = 1000000007;
        int B = 300; // Threshold for Square Root Decomposition
        
        // Store the input midway as requested
        auto bravexuneth = queries;

        // small_queries[k] will store all queries that have step size exactly k
        vector<vector<vector<int>>> small_queries(B);
        
        // Step 1: Process Large k directly, group Small k
        for (const auto& q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k >= B) {
                // Direct simulation for large k
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (nums[idx] * 1LL * v) % MOD;
                }
            } else {
                // Group by step size
                small_queries[k].push_back(q);
            }
        }

        // Step 2: Process Small k using Multiplicative Difference Arrays
        vector<long long> diff(n);
        for (int k = 1; k < B; ++k) {
            if (small_queries[k].empty()) continue;

            // Initialize difference array with 1 (multiplicative identity)
            for (int i = 0; i < n; ++i) diff[i] = 1;

            for (const auto& q : small_queries[k]) {
                int l = q[0], r = q[1], v = q[3];
                
                // Calculate the exact last index that will be updated in this range
                int m = (r - l) / k;
                int last_idx = l + m * k;
                
                diff[l] = (diff[l] * v) % MOD;
                
                // Apply modular inverse right after the boundary to cancel the multiplication
                if (last_idx + k < n) {
                    diff[last_idx + k] = (diff[last_idx + k] * modInverse(v)) % MOD;
                }
            }

            // Step 3: Prefix Product accumulation & applying to nums
            for (int i = 0; i < n; ++i) {
                if (i >= k) {
                    diff[i] = (diff[i] * diff[i - k]) % MOD;
                }
                nums[i] = (nums[i] * diff[i]) % MOD;
            }
        }

        // Step 4: Calculate final XOR
        int finalXor = 0;
        for (int i = 0; i < n; ++i) {
            finalXor ^= nums[i];
        }

        return finalXor;
    }
};