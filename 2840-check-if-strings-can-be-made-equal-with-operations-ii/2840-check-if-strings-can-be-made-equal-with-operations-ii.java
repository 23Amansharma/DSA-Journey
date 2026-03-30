class Solution {
    public boolean checkStrings(String s1, String s2) {
        // 26 letters ke liye do alag-alag frequency arrays: 
        // Ek Even indices (CSK) ke liye, ek Odd indices (MI) ke liye
        int[] evenCount = new int[26];
        int[] oddCount = new int[26];
        
        int n = s1.length();
        
        // Ek hi loop mein dono strings ko traverse karenge
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            
            if (i % 2 == 0) {
                // Even index: s1 ke character ka count badhao, s2 wale ka ghatayenge
                evenCount[c1 - 'a']++;
                evenCount[c2 - 'a']--;
            } else {
                // Odd index: s1 ke character ka count badhao, s2 wale ka ghataya
                oddCount[c1 - 'a']++;
                oddCount[c2 - 'a']--;
            }
        }
        
        //  Check karein ki kya dono arrays balance (Zero) ho gaye hain
        for (int i = 0; i < 26; i++) {
            if (evenCount[i] != 0 || oddCount[i] != 0) {
                return false; // Agar koi extra/missing character hai, toh false
            }
        }
        
        return true; // Sab match ho gaya
    }
}