class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] store = s.toCharArray();
        
        // Agar string pehle se sorted hai
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (store[i] > store[i + 1]) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;//sorted hai
        
        //  Agar length 2 hai aur sorted nahi hai toh impossible hai
        if (n == 2) return -1;
        
        // Find min and max characters
        char minChar = store[0], maxChar = store[0];
        for (char c : store){
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        // Agar smallest char starting mein hai aur poori string nahi ya max char ending mein hai
        if (store[0] == minChar || store[n - 1] == maxChar) {
            return 1;
        }
        
        // Check if transformation is possible or requires 3 vs 2 operations
        // Agar koi bhi minChar proper prefix mein nahi hai aur koi maxChar proper suffix mein nahi hai
        boolean hasMinInPrefix = false;
        for (int i = 0; i < n - 1; i++) {
            if (store[i] == minChar) {
                hasMinInPrefix = true;
                break;
            }
        }
        
        boolean hasMaxInSuffix = false;
        for (int i = 1; i < n; i++) {
            if (store[i] == maxChar) {
                hasMaxInSuffix = true;
                break;
            }
        }
        
        if (!hasMinInPrefix && !hasMaxInSuffix) {
            return 3;
        }
        
        // Baaki sabhi valid cases ke liye answer 2 hoga
        return 2;
    }
}