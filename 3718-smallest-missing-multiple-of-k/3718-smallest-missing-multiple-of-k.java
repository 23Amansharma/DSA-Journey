class Solution {
    public int missingMultiple(int[] nums, int k) {
     HashSet<Integer> set = new HashSet<>();
     for(int x : nums){
        set.add(x);
     }
     int findMultiple = k;
     while(set.contains(findMultiple)){
        findMultiple += k;
     }
     return findMultiple;
    }
}