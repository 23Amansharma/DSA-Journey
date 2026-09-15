class Solution {
    public int maxPalindromes(String s, int k) {
        int length = s.length();
        int[] maxPalindromesUpTo = new int[length + 1];

        for (int i = 0; i < length; i++) {
            maxPalindromesUpTo[i + 1] = Math.max(maxPalindromesUpTo[i + 1], maxPalindromesUpTo[i]);

            for (int center = i; center <= i + 1; center++) {
                int left = i;
                int right = center;

                while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                    int currentLength = right - left + 1;
                    if (currentLength >= k) {
                        maxPalindromesUpTo[right + 1] = Math.max(maxPalindromesUpTo[right + 1], maxPalindromesUpTo[left] + 1);
                        break;
                    }
                    left--;
                    right++;
                }
            }
        }

        return maxPalindromesUpTo[length];
    }
}