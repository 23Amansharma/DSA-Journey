class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        
        for (char c : n.toCharArray()) {
            int digit = c - '0';
            maxDigit = Math.max(maxDigit, digit);
            
            // Early exit optimization
            if (maxDigit == 9) {
                return 9;
            }
        }
        
        return maxDigit;
    }
}