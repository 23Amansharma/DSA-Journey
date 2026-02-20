import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String makeLargestSpecial(String s) {
        // Base case: nothing to process
        if (s == null || s.isEmpty()) {
            return s;
        }

        List<String> components = new ArrayList<>();
        int balance = 0;
        int start = 0;

        // Split into top-level special components
        for (int i = 0; i < s.length(); i++) {
            // 1 acts as an opening bracket, 0 as a closing bracket
            balance += (s.charAt(i) == '1') ? 1 : -1;

            // When balance is 0, we found a valid independent component
            if (balance == 0) {
                //  Extract inner string, recurse, and wrap back in 1 and 0
                String innerString = s.substring(start + 1, i);
                String sortedInner = makeLargestSpecial(innerString);
                
                components.add("1" + sortedInner + "0");
                
                // Advance start pointer for the next component
                start = i + 1;
            }
        }

        //  Sort components descending to get the lexicographically largest string
        Collections.sort(components, Collections.reverseOrder());

        // Concatenate and return
        StringBuilder result = new StringBuilder();
        for (String comp : components) {
            result.append(comp);
        }

        return result.toString();
    }
}