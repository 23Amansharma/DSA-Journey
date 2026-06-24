class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        
        // Edge Case: A sequence of length 1 has no transitions. Any number in [l, r] is valid.
        if (n == 1) {
            return m;
        }

        int size = 2 * m;
        long[][] T = new long[size][size];

        // 1. Build the Transition Matrix T
        // State [0, m-1]   -> "DOWN" states (arrived via UP step, next step MUST go strictly DOWN)
        // State [m, 2m-1]  -> "UP" states   (arrived via DOWN step, next step MUST go strictly UP)
        
        // From a DOWN state 'x', we can move to any 'y' < 'x'. 
        // Landing on 'y' via a downward step puts us in an UP state at index (y + m).
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < x; y++) {
                T[x][y + m] = 1;
            }
        }

        // From an UP state 'x', we can move to any 'y' > 'x'. 
        // Landing on 'y' via an upward step puts us in a DOWN state at index (y).
        for (int x = 0; x < m; x++) {
            for (int y = x + 1; y < m; y++) {
                T[x + m][y] = 1;
            }
        }

        // 2. Compute T^(n - 1) using Binary Matrix Exponentiation
        long[][] TnMinus1 = matrixPower(T, n - 1, size);

        // 3. Sum all entries in the final exponentiated matrix
        // (Equivalent to multiplying by a base row-vector of all 1s)
        long totalZigZagArrays = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totalZigZagArrays = (totalZigZagArrays + TnMinus1[i][j]) % MOD;
            }
        }

        return (int) totalZigZagArrays;
    }

    /**
     * Standard Binary Matrix Exponentiation: Computes (base^exp) % MOD in O(size^3 * log(exp))
     */
    private long[][] matrixPower(long[][] base, int exp, int size) {
        long[][] result = new long[size][size];
        for (int i = 0; i < size; i++) {
            result[i][i] = 1; // Initialize as Identity Matrix
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiplyMatrices(result, base, size);
            }
            base = multiplyMatrices(base, base, size);
            exp >>= 1;
        }

        return result;
    }

    /**
     * Cache-friendly Matrix Multiplication: Computes (A * B) % MOD
     */
    private long[][] multiplyMatrices(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue; // Skip zero multipliers to optimize CPU cycles
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}