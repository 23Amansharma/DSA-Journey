class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        // Track string length after each operation
        long[] len = new long[n + 1];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if (c == '*') {
                len[i + 1] = Math.max(0, len[i] - 1);
            } else if (c == '#') {
                len[i + 1] = len[i] * 2;
            } else if (c == '%') {
                len[i + 1] = len[i];
            }
        }

        // If k is out of bounds, return '.'
        if (k >= len[n]) return '.';

        // Walk backwards to find the k-th char
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (k == len[i]) return c;
            } else if (c == '*') {
                if (k >= len[i]) k++; // Reverse removal
            } else if (c == '#') {
                if (k >= len[i]) k -= len[i]; // Reverse duplication
            } else if (c == '%') {
                k = len[i] - 1 - k; // Reverse mirroring
            }
        }
        return '.';
    }
}