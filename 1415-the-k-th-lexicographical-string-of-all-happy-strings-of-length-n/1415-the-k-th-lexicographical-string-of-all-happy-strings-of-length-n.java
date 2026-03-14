class Solution {
    public String getHappyString(int n, int k) {
        int total = 3 * (1 << (n - 1));
        if (k > total) return ""; // Limit se bahar

        StringBuilder res = new StringBuilder();
        k--; // Math ke liye 0-based index

        // Pehla character (a, b, c)
        int bucket = 1 << (n - 1);
        char prev = (char) ('a' + (k / bucket));
        res.append(prev);
        k %= bucket;

        // Baaki n-1 characters ke liye (sirf 2 choices bachti hain)
        for (int i = 1; i < n; i++) {
            bucket = 1 << (n - 1 - i);
            int choice = k / bucket; // 0 ya 1
            
            // Pichle character ke basis pe direct agla valid character chuno
            char nextChar = (prev == 'a') ? (choice == 0 ? 'b' : 'c') :
                            (prev == 'b') ? (choice == 0 ? 'a' : 'c') :
                                            (choice == 0 ? 'a' : 'b');
            
            res.append(nextChar);
            prev = nextChar;
            k %= bucket;
        }

        return res.toString();
    }
}