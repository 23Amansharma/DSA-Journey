class Solution {
    public int maxLength(int[] nums) {
        int output = 2; //
        int left = 0;
        int windowProd = 1;

        for (int right = 0; right < nums.length; right++) {
            while (gcd(windowProd, nums[right]) > 1) {
                windowProd /= nums[left];
                left++;
            }
            windowProd *= nums[right];
            output = Math.max(output, right - left + 1);
        }

        return output;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
