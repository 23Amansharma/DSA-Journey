class Solution {

    int m, n;
    boolean[][] visited;
    char[][] grid;

    public boolean containsCycle(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(int x, int y, int parentX, int parentY) {

        visited[x][y] = true;

        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            // boundary check
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;

            // same character check
            if (grid[nx][ny] != grid[x][y]) continue;

            // case 1: not visited
            if (!visited[nx][ny]) {
                if (dfs(nx, ny, x, y)) return true;
            }
            // case 2: visited AND not parent
            else if (nx != parentX || ny != parentY) {
                return true;
            }
        }

        return false;
    }
}