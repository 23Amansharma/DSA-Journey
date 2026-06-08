class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] arr = new int[nums.length];
        int i = 0;
        for (int x : nums) if (x < pivot) arr[i++] = x;
        for (int x : nums) if (x == pivot) arr[i++] = x;
        for (int x : nums) if (x > pivot) arr[i++] = x;
        return arr;
    }
}