class Solution {
    public boolean canBeEqual(String s1, String s2) {
        
      
        // TEAM CSK (YELLOW)  Even Indices (0 and 2)

        // Can we match the CSK players?
        // Path 1: Direct Match (Players are already in the exact same seats)
        // Path 2: Swap Match (Players are there, but need to swap seats 0 and 2)
        boolean cskMatch = (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                           (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0));
        
    
        // TEAM MI (BLUE)  Odd Indices (1 and 3)
     
        // Can we match the MI players?
        // Path 1: Direct Match (Players are already in the exact same seats)
        // Path 2: Swap Match (Players are there, but need to swap seats 1 and 3)
        boolean miMatch = (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                          (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));
        
        // Both CSK and MI must successfully match their respective players 
        // for the entire string to be equal.
        return cskMatch && miMatch;
    }
}