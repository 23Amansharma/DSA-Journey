class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        
        // Step 1: Simulate gravity for each row
        for (int r = 0; r < m; r++) {
            int emptyIndex = n - 1; // Tracks the rightmost available empty cell
            for (int c = n - 1; c >= 0; c--) {
                if (boxGrid[r][c] == '*') {
                    // Obstacle found, stones can't fall past it. 
                    // Reset emptyIndex to just before the obstacle.
                    emptyIndex = c - 1;
                } else if (boxGrid[r][c] == '#') {
                    // Stone found, make it fall to the emptyIndex
                    if (c != emptyIndex) {
                        boxGrid[r][emptyIndex] = '#';
                        boxGrid[r][c] = '.';
                    }
                    emptyIndex--;
                }
            }
        }
        
        // Step 2: Rotate the box 90 degrees clockwise
        char[][] rotatedBox = new char[n][m];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                rotatedBox[c][m - 1 - r] = boxGrid[r][c];
            }
        }
        
        return rotatedBox;
    }
}