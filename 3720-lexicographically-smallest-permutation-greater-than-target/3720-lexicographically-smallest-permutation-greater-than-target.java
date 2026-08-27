class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Try to match the longest prefix of target
        char[] result = new char[n];
        int matchLen = 0;
        
        for (int i = 0; i < n; i++) {
            char t = target.charAt(i);
            if (freq[t - 'a'] > 0) {
                result[i] = t;
                freq[t - 'a']--;
                matchLen++;
            } else {
                break;
            }
        }
        
        //  Backtrack from matchLen down to 0
        for (int i = matchLen; i >= 0; i--) {
            // Restore character used at index i if backtracked from i
            if (i < matchLen) {
                freq[result[i] - 'a']++;
            }
            
            if (i < n) {
                char t = target.charAt(i);
                // Look for the smallest available char strictly greater than target[i]
                for (int c = t - 'a' + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        result[i] = (char) ('a' + c);
                        freq[c]--;
                        
                        // Fill remaining slots with smallest available characters
                        int idx = i + 1;
                        for (int ch = 0; ch < 26; ch++) {
                            while (freq[ch] > 0) {
                                result[idx++] = (char) ('a' + ch);
                                freq[ch]--;
                            }
                        }
                        return new String(result);
                    }
                }
            }
        }
        
        return "";
    }
}