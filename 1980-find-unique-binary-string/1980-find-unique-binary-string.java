class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        char[] unique = new char[n];

        for (int i = 0; i < n; i++) {
            char current = nums[i].charAt(i);
            unique[i] = current == '0' ? '1' : '0';
        }

        return new String(unique);
    }
}