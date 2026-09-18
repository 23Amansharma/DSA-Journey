class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        java.util.Arrays.fill(left, n);
        
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = Math.max(right[idx], i);
        }
        
        List<int[]> intervals = new java.util.ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (left[i] == n) continue;
            
            int l = left[i], r = right[i];
            boolean valid = true;
            
            for (int j = l; j <= r; j++) {
                int idx = s.charAt(j) - 'a';
                if (left[idx] < l) {
                    valid = false;
                    break;
                }
                r = Math.max(r, right[idx]);
            }
            
            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }
        
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> result = new java.util.ArrayList<>();
        int prevEnd = -1;
        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }
        
        return result;
    }
}
