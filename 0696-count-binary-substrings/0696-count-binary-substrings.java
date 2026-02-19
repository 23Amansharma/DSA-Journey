class Solution {
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        int totalCount = 0;
        int prevRun = 0;
        int currRun = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currRun++; // Extend current group
            } else {
                totalCount += Math.min(prevRun, currRun); // Add valid pairs
                prevRun = currRun; // Shift to previous
                currRun = 1; // Reset for new group
            }
        }
        
        totalCount += Math.min(prevRun, currRun); // Add final pair
        return totalCount;
    }
}