import java.util.*;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {

        List<String> ans = new ArrayList<>();

        // Step 1: Check each query
        for (String q : queries) {

            // Step 2: If it matches any dictionary word
            if (isValid(q, dictionary)) {
                ans.add(q);
            }
        }

        return ans;
    }

    // Helper function to check validity
    private boolean isValid(String q, String[] dictionary) {

        // Compare with every dictionary word
        for (String d : dictionary) {

            int diff = 0;

            // Compare characters
            for (int i = 0; i < q.length(); i++) {

                if (q.charAt(i) != d.charAt(i)) {
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