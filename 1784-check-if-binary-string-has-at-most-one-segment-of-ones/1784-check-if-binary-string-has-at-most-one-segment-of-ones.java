class Solution {
    public boolean checkOnesSegment(String s) {
        
        boolean zeroMilChuka = false; // track karega ki kya '0' aa chuka hai
        
        for (int i = 0; i < s.length(); i++) {
            
            char currentChar = s.charAt(i);
            
            if (currentChar == '0') {
                // agar 0 mil gaya to mark kar  dena hai
                zeroMilChuka = true;
            } 
            else { // currentChar == '1'
                
                // agar 1 mil raha hai aur pehle hi 0 aa chuka hai
                // matlab 1 ka naya segment start ho gaya -> invalid
                if (zeroMilChuka) {
                    return false;
                }
            }
        }
        
        // agar loop complete ho gaya to max ek hi segment tha
        return true;
    }
}