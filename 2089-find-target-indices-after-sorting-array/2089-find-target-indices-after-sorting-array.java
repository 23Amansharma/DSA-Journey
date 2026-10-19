class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int count = 0;
        int frq = 0;
        // Count elements smaller than target and elements equal to target
        for (int x : nums) {
            if (x < target) {
                count++;
            } else if (x == target) {
                frq++;
            }
        }
        
        List<Integer> store = new ArrayList<>();
        
        // Add indices directly without sorting
        for (int i = 0; i < frq; i++) {
            store.add(count + i);
        }
        
        return store;
    }
}