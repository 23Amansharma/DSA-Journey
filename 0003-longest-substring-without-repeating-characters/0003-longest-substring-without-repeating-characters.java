class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] lastIndex = new int[128];
        java.util.Arrays.fill(lastIndex, -1);
        
        int start = 0;
        for (int end = 0; end < n; end++) {
            char currChar = s.charAt(end);
            if (lastIndex[currChar] >= start) {
                start = lastIndex[currChar] + 1;
            }
            lastIndex[currChar] = end;
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }
}
