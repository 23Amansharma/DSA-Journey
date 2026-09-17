class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minL = new int[n];
        Arrays.fill(minL, Integer.MAX_VALUE);
        
        int sum = 0, l = 0, ans = Integer.MAX_VALUE, curMin = Integer.MAX_VALUE;
        
        for (int r = 0; r < n; r++) {
            sum += arr[r];
            
            while (sum > target) {
                sum -= arr[l++];
            }
            
            if (sum == target) {
                int len = r - l + 1;
                if (l > 0 && minL[l - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + minL[l - 1]);
                }
                curMin = Math.min(curMin, len);
            }
            
            minL[r] = curMin;
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
// Time Complexity: O(N)
// Space Complexity: O(N)