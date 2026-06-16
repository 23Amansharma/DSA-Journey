class Solution {
    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                // Remove the last character if it exists
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else if (ch  == '#') {
                // Duplicate the current result and append to itself
                sb.append(sb.toString());
            } else if (ch == '%') {
                // Reverse the current result
                sb.reverse();
            } else {
                // Append lowercase English letter
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}