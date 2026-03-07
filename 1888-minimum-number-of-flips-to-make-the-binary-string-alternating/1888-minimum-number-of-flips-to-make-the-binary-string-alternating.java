class Solution {
    public int minFlips(String s) {

        int n = s.length();

        // rotation simulate karne ke liye string ko double kar diya
        String doubled = s + s;

        int mismatchStartWithZero = 0; // pattern: 010101...
        int mismatchStartWithOne = 0;  // pattern: 101010...

        int ans = Integer.MAX_VALUE;

        int left = 0;

        // sliding window traverse kar rahe hain doubled string par
        for (int right = 0; right < doubled.length(); right++) {

            char current = doubled.charAt(right);

            // even index par expected kya hona chahiye alternating pattern me
            char expectedZeroPattern = (right % 2 == 0) ? '0' : '1';
            char expectedOnePattern = (right % 2 == 0) ? '1' : '0';

            // agar match nahi hua to flip required
            if (current != expectedZeroPattern) mismatchStartWithZero++;
            if (current != expectedOnePattern) mismatchStartWithOne++;

            // window size n se bada ho gaya to left shrink karna padega
            if (right - left + 1 > n) {

                char leftChar = doubled.charAt(left);

                char leftExpectedZero = (left % 2 == 0) ? '0' : '1';
                char leftExpectedOne = (left % 2 == 0) ? '1' : '0';

                // jo mismatch add kiya tha usko remove kar rahe hain
                if (leftChar != leftExpectedZero) mismatchStartWithZero--;
                if (leftChar != leftExpectedOne) mismatchStartWithOne--;

                left++;
            }

            // jab valid window size n ka mil gaya tab answer update
            if (right - left + 1 == n) {

                // dono patterns me se minimum flips lena hai
                ans = Math.min(ans, Math.min(mismatchStartWithZero, mismatchStartWithOne));
            }
        }

        return ans;
    }
}