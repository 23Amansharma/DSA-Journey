class Solution {
    public String maxSumOfSquares(int num, int sum) {
      
        if (sum < 1 || sum > (long) num * 9)
            return "";
        
        StringBuilder sb = new StringBuilder();
        int remainingSum = sum;
        
        for (int i = 0; i < num; i++) {
            int remainingSlots = num - 1 - i;
            
            // For the very first position (i == 0) and num > 1, 
            // the digit must be at least 1 to avoid leading zeros.
            int minPossible = (i == 0 && num > 1) ? Math.max(1, remainingSum - remainingSlots * 9) 
                                                : Math.max(0, remainingSum - remainingSlots * 9);
            int maxPossible = Math.min(9, remainingSum);
            
            int bestDigit = 0;
            for (int d = maxPossible; d >= minPossible; d--) {
                if (remainingSum - d >= 0 && remainingSum - d <= remainingSlots * 9) {
                    bestDigit = d;
                    break;
                }
            }
            
            sb.append(bestDigit);
            remainingSum -= bestDigit;
        }
        
        return sb.toString();
    }
}