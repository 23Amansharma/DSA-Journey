class Solution {
    public boolean predictTheWinner(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }
        for (int diff = 1; diff < nums.length; diff++) {
            for (int left = 0; left < nums.length - diff; left++) {
                int right = left + diff;
                arr[left] = Math.max(nums[left] - arr[left + 1], nums[right] - arr[left]);
            }
        }

        return arr[0] >= 0;
    }
}
