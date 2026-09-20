class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int revAlphabetIndex = 26 - (ch - 'a');
            int stringIndex = i + 1; 
            
            totalSum += revAlphabetIndex * stringIndex;
        }
        
        return totalSum;
    }
}
