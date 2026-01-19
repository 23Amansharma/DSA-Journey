class Solution {
    public String reverseVowels(String s) {
        //String ko char array mein badla taaki swap kar saku
        char[] word = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        String vowels = "aeiouAEIOU"; // Check karne ke liye easy padega

        //  Jab tak pointers cross na karein
        while (left < right) {
            
            // Left se vowel pta krne ke liye
            while (left < right && vowels.indexOf(word[left]) == -1) {
                left++;
            }
            
            // Right se vowel pta krne ke liye
            while (left < right && vowels.indexOf(word[right]) == -1) {
                right--;
            }
            
            // Dono vowels mil gaye, ab swap karunga
            char temp = word[left];
            word[left] = word[right];
            word[right] = temp;
            
            // Swap ke baad aage ka process
            left++;
            right--;
        }
        
        // Array ko wapas String bana kar return krna
        return new String(word);
    }
}