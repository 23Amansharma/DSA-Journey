import java.util.Arrays;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;

        // Step 1: Process the matrix row by row to build vertical histograms
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the current cell is 1 and it's not the top row, 
                // accumulate the continuous height from the row directly above.
                if (matrix[i][j] == 1 && i > 0) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }

            // Step 2: Extract and sort the current row's heights
            // We clone the row to sort it without permanently scrambling the original columns.
            // Arrays.sort() sorts in ascending order.
            int[] sortedHeights = matrix[i].clone();
            Arrays.sort(sortedHeights);

            // Step 3: Calculate the maximum bounding box for the current row
            // We iterate from right to left since the tallest heights are at the end of the array.
            for (int j = n - 1; j >= 0; j--) {
                int currentHeight = sortedHeights[j];
                
                // If we hit a height of 0, no further submatrices can be formed
                if (currentHeight == 0) {
                    break; 
                }
                
                // The width is the number of columns processed so far from the right
                int currentWidth = n - j; 
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }
        }

        return maxArea;
    }
}