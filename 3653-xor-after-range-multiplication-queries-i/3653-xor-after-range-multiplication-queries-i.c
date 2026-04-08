int xorAfterQueries(int* nums, int numsSize, int** queries, int queriesSize, int* queriesColSize) {
    long long MOD = 1000000007; // 10^9 + 7
    
    // Process each query
    for (int i = 0; i < queriesSize; i++) {
        int l = queries[i][0];
        int r = queries[i][1];
        int k = queries[i][2];
        long long v = queries[i][3];
        
        // Apply multiplication with step size k
        for (int idx = l; idx <= r; idx += k) {
            nums[idx] = (int)(((long long)nums[idx] * v) % MOD);
        }
    }
    
    // Calculate the final XOR of all elements
    int finalXor = 0;
    for (int i = 0; i < numsSize; i++) {
        finalXor ^= nums[i];
    }
    
    return finalXor;
}