class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        
        char mid = '*';
        int halfLen = 0;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                mid = (char) ('a' + i);
            }
            halfFreq[i] = freq[i] / 2;
            halfLen += halfFreq[i];
        }
        
        long maxK = k + 1;
        long[][] comb = new long[halfLen + 1][halfLen + 1];
        for (int i = 0; i <= halfLen; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
                if (comb[i][j] > maxK) {
                    comb[i][j] = maxK;
                }
            }
        }
        
        StringBuilder leftHalf = new StringBuilder();
        long currentK = k;
        int remainingLen = halfLen;
        
        for (int i = 0; i < halfLen; i++) {
            boolean placed = false;
            for (int j = 0; j < 26; j++) {
                if (halfFreq[j] > 0) {
                    halfFreq[j]--;
                    remainingLen--;
                    
                    long ways = 1;
                    int curLen = remainingLen;
                    for (int c = 0; c < 26; c++) {
                        if (halfFreq[c] > 0) {
                            long cValue = comb[curLen][halfFreq[c]];
                            if (ways == 0 || cValue == 0) {
                                ways = 0;
                            } else if (ways > maxK / cValue) {
                                ways = maxK;
                            } else {
                                ways *= cValue;
                            }
                            curLen -= halfFreq[c];
                        }
                    }
                    
                    if (currentK <= ways) {
                        leftHalf.append((char) ('a' + j));
                        placed = true;
                        break;
                    } else {
                        currentK -= ways;
                        halfFreq[j]++;
                        remainingLen++;
                    }
                }
            }
            if (!placed) {
                return "";
            }
        }
        
        String left = leftHalf.toString();
        String right = leftHalf.reverse().toString();
        if (mid != '*') {
            return left + mid + right;
        }
        return left + right;
    }
}
