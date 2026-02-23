class Solution {
    public boolean hasAllCodes(String s, int k) {
        int totalCodes = 1 << k; // 2^k
        
        // Early Exit: Agar string itni badi hi nahi hai ki usme saare combinations aa sakein
        if (s.length() < totalCodes + k - 1) {
            return false;
        }
        
        HashSet<String> seen = new HashSet<>();
        
        // Sliding window se k size ke substrings nikalo
        for (int i = 0; i <= s.length() - k; i++) {
            seen.add(s.substring(i, i + k));
            
            // Agar saare unique combinations mil gaye, toh aage check karne ki zaroorat nahi
            if (seen.size() == totalCodes) {
                return true;
            }
        }
        
        return false;
    }
}