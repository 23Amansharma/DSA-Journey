class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        int[] freq = new int[n + 1];
        int commonCount = 0;

        for (int i = 0; i < n; i++) {
            // Increment frequency for element in A
            freq[A[i]]++;
            // If frequency becomes 2, it's common to both prefixes
            if (freq[A[i]] == 2) commonCount++;

            // Increment frequency for element in B
            freq[B[i]]++;
            // If frequency becomes 2, it's common to both prefixes
            if (freq[B[i]] == 2) commonCount++;

            res[i] = commonCount;
        }

        return res;
    }
}