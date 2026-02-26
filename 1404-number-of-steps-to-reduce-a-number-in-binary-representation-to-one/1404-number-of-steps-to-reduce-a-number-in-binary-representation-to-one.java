class Solution {
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        
        // Traverse from the least significant bit up to index 1
        for (int i = s.length() - 1; i > 0; i--) {
            int currentBit = s.charAt(i) - '0';
            
            if (currentBit + carry == 1) {
                // The bit evaluates to 1 (odd). 
                // We add 1 (makes carry = 1) and divide by 2 (2 steps total).
                steps += 2;
                carry = 1;
            } else {
                // The bit evaluates to 0 or 2 (even).
                // We divide by 2 (1 step total). Carry remains whatever it was.
                steps += 1;
            }
        }
        
        // If carry is 1, the most significant bit '1' becomes '10', 
        // requiring one final step to reduce.
        return steps + carry;
    }
}