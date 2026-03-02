class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];

        // Count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int j = n - 1;
            while (j >= 0 && grid[i][j] == 0) j--;
            zeros[i] = n - 1 - j;
        }

        int swaps = 0;

        // Greedily place correct row at position i
        for (int i = 0; i < n; i++) {
            int need = n - 1 - i;
            int j = i;

            while (j < n && zeros[j] < need) j++;
            if (j == n) return -1;

            while (j > i) {
                int temp = zeros[j];
                zeros[j] = zeros[j - 1];
                zeros[j - 1] = temp;
                swaps++;
                j--;
            }
        }

        return swaps;
    }
}