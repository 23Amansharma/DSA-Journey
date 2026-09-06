class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // Grid array: arr[i][j] stores ways to form t[0..j-1] from s[0..i-1]
        int[][] arr = new int[m + 1][n + 1];

        // Base case: Empty target string 't' can always be formed in 1 way
        for (int i = 0; i <= m; i++) {
            arr[i][0] = 1;
        }

        // Fill the array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                } else {
                    // If characters don't match, skip current character of 's'
                    arr[i][j] = arr[i - 1][j];
                }
            }
        }

        // Final result: ways to form complete string 't' from complete string 's'
        return arr[m][n];
    }
}