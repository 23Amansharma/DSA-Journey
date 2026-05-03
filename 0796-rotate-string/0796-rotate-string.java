class Solution {
    public boolean rotateString(String s, String goal) {
        // If lengths aren't equal, s can never become goal
        if (s.length() != goal.length()) {
            return false;
        }
        
        // Combine s with itself and check if goal exists inside it
        String doubled = s + s;
        return doubled.contains(goal);
    }
}