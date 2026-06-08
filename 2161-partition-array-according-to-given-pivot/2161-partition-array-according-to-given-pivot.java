class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] arr= new int[nums.length];
        int idx = 0;
        for (int x : nums) {
            if (x < pivot) arr[idx++] = x;
        }
        for (int x : nums) {
            if (x == pivot) arr[idx++] = x;
        }
        for (int x : nums) {
            if (x > pivot) arr[idx++] = x;
        }

        return arr;
    }
}