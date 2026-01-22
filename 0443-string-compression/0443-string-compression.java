class Solution {
    public int compress(char[] chars) {

        int write = 0;   // compressed string ka index
        int read = 0;    // input array ka index

        while (read < chars.length) {
            char currentChar = chars[read];
            int count = 0;

            // same characters count kara
            while (read < chars.length && chars[read] == currentChar) {
                read++;
                count++;
            }

            // character likho
            chars[write++] = currentChar;

            // agar count > 1 hai to digits likho
            if (count > 1) {
                String freq = String.valueOf(count);
                for (char digit : freq.toCharArray()) {
                    chars[write++] = digit;
                }
            }
        }
        return write;
    }
}
