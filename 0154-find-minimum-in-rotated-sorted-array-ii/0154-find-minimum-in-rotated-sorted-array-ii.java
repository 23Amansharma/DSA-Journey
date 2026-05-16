class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum must be in the right half
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Minimum is at mid or to the left
                right = mid;
            } else {
                // When nums[mid] == nums[right], we don't know which side to go.
                // Shrink the window by 1 to bypass the duplicate.
                right--;
            }
        }
        return nums[left];
    }
}
