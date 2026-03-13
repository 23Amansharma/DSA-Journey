class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long minW = Integer.MAX_VALUE;
        for (int w : workerTimes) minW = Math.min(minW, w);
        
        long left = 0, right = minW * mountainHeight * (mountainHeight + 1L) / 2L, ans = right;
        
        while (left <= right) {
            long mid = left + (right - left) / 2L;
            if (check(mid, mountainHeight, workerTimes)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    private boolean check(long t, int h, int[] wt) {
        long reduced = 0;
        for (int w : wt) {
            reduced += (long) ((Math.sqrt(1.0 + 8.0 * t / w) - 1.0) / 2.0);
            if (reduced >= h) return true;
        }
        return false;
    }
}