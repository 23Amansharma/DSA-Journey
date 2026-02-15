class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder(); // To build the result efficiently
        int i = a.length() - 1; // Pointer for string a (end)
        int j = b.length() - 1; // Pointer for string b (end)
        int carry = 0;          // To store carry (0 or 1)

        // Loop until both strings are finished or carry remains
        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry; // Start sum with previous carry

            // If digits left in a, add to sum
            if (i >= 0) {
                sum += a.charAt(i) - '0'; // Convert char to int
                i--;
            }

            // If digits left in b, add to sum
            if (j >= 0) {
                sum += b.charAt(j) - '0'; // Convert char to int
                j--;
            }

            sb.append(sum % 2); // Append the result bit (0 or 1)
            carry = sum / 2;    // Calculate new carry (0 or 1)
        }

        // Result is backwards, so reverse it
        return sb.reverse().toString();
    }
}