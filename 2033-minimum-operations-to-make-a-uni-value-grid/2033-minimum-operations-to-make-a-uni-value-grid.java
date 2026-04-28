import java.util.Arrays;

class Solution {

    public int minOperations(int[][] grid, int stepSize) {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        int totalElements = rowCount * colCount;

        int[] flattenedValues = new int[totalElements];
        int index = 0;

        // Step 1: Flatten the grid
        for (int[] row : grid) {
            for (int value : row) {
                flattenedValues[index++] = value;
            }
        }

        // Step 2: Check feasibility (same remainder mod stepSize)
        int remainder = flattenedValues[0] % stepSize;
        for (int value : flattenedValues) {
            if (value % stepSize != remainder) {
                return -1;
            }
        }

        // Step 3: Sort to find median
        Arrays.sort(flattenedValues);
        int targetValue = flattenedValues[totalElements / 2];

        // Step 4: Calculate minimum operations
        int operationCount = 0;
        for (int value : flattenedValues) {
            operationCount += Math.abs(value - targetValue) / stepSize;
        }

        return operationCount;
    }
}