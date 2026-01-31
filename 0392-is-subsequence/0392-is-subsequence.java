class Solution {
    public boolean isSubsequence(String s, String t) {
        
        // Agar s empty hai to wo hamesha subsequence maana jayega
        if (s.length() == 0) return true;

        int sIndex = 0; // Ye variable s string ke characters ko track karega

        // Maine t string par loop chalaya taaki har character check kar sakein
        for (int tIndex = 0; tIndex < t.length(); tIndex++) {
            
            // Yahan check kiya: Agar t ka character s ke character se match karta hai
            // toh sIndex badhaya taaki s ka agla character dhund sakein
            if (t.charAt(tIndex) == s.charAt(sIndex)) {
                sIndex++;
            }

            // Optimization: Agar sIndex last tak pahunch gaya, matlab saare characters mil gaye
            // toh aage loop chalane ki need nahi hai, yahin se true return kar diya
            if (sIndex == s.length()) {
                return true;
            }
        }

        // Agar loop poora chal gaya aur sIndex end tak nahi pahuncha, iska matlab subsequence nahi hai
        return false;
    }
}