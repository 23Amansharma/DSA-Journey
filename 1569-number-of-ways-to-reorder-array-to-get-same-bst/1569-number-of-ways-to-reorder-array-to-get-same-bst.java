class Solution {
    private static final long MOD = 1000000007;
         private long[][] table;
      public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute Pascal's triangle for combinations C(n, k)
        table = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums)
            list.add(num);
        // Subtract 1 because the problem asks for *reorderings* other than the original array itself
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) return 1;

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root)left.add(nums.get(i));
                 else  right.add(nums.get(i));
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        int lSize = left.size();
        int rSize = right.size();

        // Ways to interleave left and right subtree elements: C(lSize + rSize, lSize)
        long ways = table[lSize + rSize][lSize];
        
        // Multiply by the number of ways to arrange the subtrees themselves
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}