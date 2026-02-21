class Solution {
    // Bitmask technique for O(1) prime lookup.
    // Constraints ke according max 20 bits ho sakte hain. 
    // Humne 2nd, 3rd, 5th, 7th, 11th, 13th, 17th, aur 19th position par '1' set kiya hai.
    private static final int PRIME_BITMASK = 0b10100010100010101100;

    public int countPrimeSetBits(int left, int right) {
        int primeSetBitsCount = 0;

        // Loop through the inclusive range
        for (int currentNumber = left; currentNumber <= right; currentNumber++) {
            
            // In-built highly optimized method to count set bits (1s)
            int activeBitsCount = Integer.bitCount(currentNumber);
            
            // Fast bitwise check: Shift 1 by 'activeBitsCount' and check against the mask
            if ((PRIME_BITMASK & (1 << activeBitsCount)) != 0) {
                primeSetBitsCount++;
            }
        }

        return primeSetBitsCount;
    }
}