class Solution {
    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int index = s.length() - 1;

        while (index >= 0) {
            while (index >= 0 && s.charAt(index) == ' ') {
                index--;
            }
            if (index < 0) break;

            int wordEnd = index;
            while (index >= 0 && s.charAt(index) != ' ') {
                index--;
            }
            int wordStart = index + 1;

            if (result.length() > 0) {
                result.append(' ');
            }
            
            result.append(s, wordStart, wordEnd + 1);
        }

        return result.toString();
    }
}