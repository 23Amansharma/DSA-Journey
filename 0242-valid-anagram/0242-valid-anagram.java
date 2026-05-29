import java.util.HashMap;

class Solution {
    public boolean isAnagram(String s, String t) {
        // 1. If lengths are not equal, they cannot be anagrams
        if (s.length() != t.length()) return false;
        
        HashMap<Character, Integer> mp1 = new HashMap<>();
        
        // 2. Add characters of first string 's' to map and increase count
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            mp1.put(ch, mp1.getOrDefault(ch, 0) + 1);
        }
        
        // 3. Decrease count for characters of second string 't'
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            
            // If character is not present in map, return false
            if (!mp1.containsKey(ch)) return false;
            
            int freq = mp1.get(ch);
            mp1.put(ch, freq - 1);
        }
        
        // 4. Check if all frequencies in map are exactly 0
        for (int val : mp1.values()) {
            // If any frequency is not 0, it means characters did not match perfectly
            if (val != 0) return false;
        }  
        
        // 5. If all checks pass, strings are anagrams
        return true;  
    }

    /* 
    // FIRST METHOD APPROACH 
    // This approach calls the helper method 2 times and compares both maps.
    
    public boolean isAnagramFirstApproach(String s, String t) {
        if(s.length() != t.length()) return false;
        
        HashMap<Character, Integer> mp1 = method(s);
        HashMap<Character, Integer> mp2 = method(t);
        
        return mp1.equals(mp2); 
    }
    
    HashMap<Character, Integer> method(String s) {
        HashMap<Character, Integer> mapname = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(!mapname.containsKey(ch)) {
                mapname.put(ch, 1);
            } else {
                int currentFrequency = mapname.get(ch);
                mapname.put(ch, currentFrequency + 1);
            }
        }
        return mapname;
    }
    */
}
