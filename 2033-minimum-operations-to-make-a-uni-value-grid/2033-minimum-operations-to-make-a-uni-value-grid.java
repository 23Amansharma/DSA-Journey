import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int size = m * n;
        int[] arr = new int[size];
        
        // Flatten grid
        int idx = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val;
            }
        }
        
        // Check feasibility
        int mod = arr[0] % x;
        for (int val : arr) {
            if (val % x != mod) {
                return -1;
            }
        }
        
        // Sort and find median
        Arrays.sort(arr);
        int median = arr[size / 2];
        
        // Calculate operations
        int operations = 0;
        for (int val : arr) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}