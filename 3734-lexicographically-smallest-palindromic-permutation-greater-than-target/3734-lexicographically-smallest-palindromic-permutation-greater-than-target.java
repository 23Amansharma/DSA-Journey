class Solution {
    /*
     * Approach / Technique:
     * 1. Greedy Prefix Matching with Backtracking (Next-Permutation Logic)
     *    - First, validate if a palindromic permutation of 's' exists (at most 1 character with an odd frequency).
     *    - Extract the frequencies for the left half (freq / 2) and store the middle character if the string length is odd.
     *    - Iterate backwards from the maximum prefix match length down to 0:
     *      a) Try matching 'matchLen' characters of the left half with 'target'.
     *      b) At position 'matchLen', place the smallest available character that is strictly GREATER than 'target[matchLen]'.
     *      c) Fill the remaining positions of the left half greedily with the smallest available characters to ensure lexicographical minimality.
     *      d) Construct the full string (Left Half + Middle + Reversed Left Half) and return the first valid string found.
     * 
     * 2. Brute Force (Alternative - Not Recommended)
     *    - Generate all unique palindromic permutations in sorted order and find the first one > 'target'.
     *    - Time Limit Exceeded (TLE) for n <= 300.
     *
     * Total Methods Possible: 2 (Greedy Prefix Matching & Brute Force)
     *
     * Time Complexity:  O(N * Σ) where N = s.length() and Σ = 26 (Alphabet size).
     * Space Complexity: O(N) auxiliary space for arrays and result string construction.
     */
    public String lexPalindromicPermutation(String src, String target) {
        int len = src.length();
        int[] freq = new int[26];
        for (int i = 0; i < len; i++) {
            freq[src.charAt(i) - 'a']++;
        }

        // Check if palindrome permutation is possible
        int oddCnt = 0;
        char midCh = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCnt++;
                midCh = (char) ('a' + i);
            }
        }
        if (oddCnt > 1) return "";

        int halfLen = len / 2;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        // Try prefix lengths from largest to 0
        for (int matchLen = halfLen; matchLen >= 0; matchLen--) {
            int[] avail = halfFreq.clone();
            char[] left = new char[halfLen];
            boolean valid = true;

            // Build matching prefix
            for (int i = 0; i < matchLen; i++) {
                char ch = target.charAt(i);
                if (avail[ch - 'a'] > 0) {
                    left[i] = ch;
                    avail[ch - 'a']--;
                } else {
                    valid = false;
                    break;
                }
            }
            if (!valid) continue;

            // Full prefix matches exact target half
            if (matchLen == halfLen) {
                String candidate = build(left, midCh, len);
                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
                continue;
            }

            // Pick strictly larger char at index matchLen
            char minGreater = target.charAt(matchLen);
            for (int c = minGreater - 'a' + 1; c < 26; c++) {
                if (avail[c] > 0) {
                    left[matchLen] = (char) ('a' + c);
                    avail[c]--;

                    // Fill rest with smallest available characters
                    int idx = matchLen + 1;
                    for (int j = 0; j < 26; j++) {
                        while (avail[j] > 0) {
                            left[idx++] = (char) ('a' + j);
                            avail[j]--;
                        }
                    }

                    return build(left, midCh, len);
                }
            }
        }

        return "";
    }

    private String build(char[] left, char midCh, int totalLen) {
        StringBuilder res = new StringBuilder();
        res.append(left);
        if (totalLen % 2 != 0) {
            res.append(midCh);
        }
        for (int i = left.length - 1; i >= 0; i--) {
            res.append(left[i]);
        }
        return res.toString();
    }
}