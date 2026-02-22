class Solution {
    public int binaryGap(int n) {
        int maxGap = 0;
        int lastPos = -1; // Pehla '1' abhi nahi mila
        int currPos = 0;

        while (n > 0) {
            if ((n & 1) == 1) { // Check karo agar current bit '1' hai
                if (lastPos != -1) {
                    maxGap = Math.max(maxGap, currPos - lastPos); // Max gap update karo
                }
                lastPos = currPos; // Naye '1' ki position yaad rakho
            }
            n >>= 1; // Number ko 1 bit right shift karo
            currPos++; // Position counter badhao
        }

        return maxGap;
    }
}