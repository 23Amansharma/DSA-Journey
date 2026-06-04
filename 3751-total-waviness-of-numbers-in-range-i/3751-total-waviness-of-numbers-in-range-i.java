class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWav = 0; 
        // Range [num1, num2] ke har number ke liye iteration
        for (int i = num1; i <= num2; i++) {
            totalWav += getWaviness(i);
        }
        
        return totalWav;
    }
    
    private int getWaviness(int n) {
        // Number ko string mein convert karke digits access karna aasaan hai
        String s = String.valueOf(n);
        int len = s.length();
        
        // 3 se kam digits waale numbers ki waviness 0 hoti hai
        if (len < 3) return 0;
        
        int count = 0;
        // Pehla aur aakhri digit check nahi karna hai
        for (int i = 1; i < len - 1; i++) {
            int prev = s.charAt(i - 1) - '0';
            int curr = s.charAt(i) - '0';
            int next = s.charAt(i + 1) - '0';
            
            // Peak check
            if (curr > prev && curr > next) {
                count++;
            } 
            // Valley check
            else if (curr < prev && curr < next) {
                count++;
            }
        }
        return count;
    }
}