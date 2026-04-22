class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {

        List<String> ans = new ArrayList<>();

        //  Check each query
        for (String word : queries) {

            // If it matches any dictionary word
            if (isValid(word, dictionary)) {
                ans.add(word);
            }
        }

        return ans;
    }

    // Helper function to check validity
    private boolean isValid(String word, String[] dictionary) {

        // Compare with every dictionary word
        for (String word2 : dictionary) {

            int diff = 0;

            // Compare characters
            for (int i = 0; i < word.length(); i++) {

                if (word.charAt(i) != word2.charAt(i)) {
                    diff++;

                    // Stop early if more than 2 edits needed
                    if (diff > 2) break;
                }
            }

            // If any dictionary word matches within 2 edits
            if (diff <= 2) return true;
        }

        return false; // no match found
    }
}