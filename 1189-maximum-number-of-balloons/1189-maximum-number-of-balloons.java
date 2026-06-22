class Solution {
    public int maxNumberOfBalloons(String text) {
        int n = text.length(); 
        int[] freq = new int[26];

        //  Tally all character frequencies in a single pass
        for (int i = 0; i < n; i++) {
            freq[text.charAt(i) - 'a']++;
        }

        // Identify the limiting factor among the 5 essential letters
        int minWords = freq['b' - 'a'];
        minWords = Math.min(minWords, freq['a' - 'a']);
        minWords = Math.min(minWords, freq['l' - 'a'] / 2); // Requires 2 'l's
        minWords = Math.min(minWords, freq['o' - 'a'] / 2); // Requires 2 'o's
        minWords = Math.min(minWords, freq['n' - 'a']);

        return minWords;
    }
}