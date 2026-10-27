class Solution {
    public String[] createGrid(int m, int n) {
        String[] result = new String[m];
        StringBuilder firstRow = new StringBuilder();
        for (int j = 0; j < n; j++) {
            firstRow.append('.');
        }
        result[0] = firstRow.toString();
        for (int i = 1; i < m; i++) {
            StringBuilder rowStr = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j == n - 1) {
                    rowStr.append('.');
                } else {
                    rowStr.append('#');
                }
            }
            result[i] = rowStr.toString();
        }
        
        return result;
    }
}