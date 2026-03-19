class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // m+1, n+1 used to avoid extra out of bound if-else conditions (1-based indexing)
        int sum[][] = new int[m+1][n+1]; 
        int count[][] = new int[m+1][n+1]; 
        int find = 0;
        
        // Changed i < m-1 to i < m AND j < n-1 to j < n
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int value = 0; 
                int isX = 0;
                
                // Check condition
                if(grid[i][j] == 'X'){
                    value = 1;
                    isX = 1;
                } else if (grid[i][j] == 'Y') {
                    value = -1;
                }

                // Apply 2D prefix sum formula
                sum[i+1][j+1]  = value + sum[i][j+1] + sum[i+1][j] - sum[i][j];
                count[i+1][j+1] = isX + count[i][j+1] + count[i+1][j] - count[i][j];
                
                //  Changed & to && for Logical AND
                if(sum[i+1][j+1] == 0 && count[i+1][j+1] > 0){
                    find++;
                }
            }
        }
        return find;
    }
}