class Solution {
    public int sumCounts(List<Integer> nums) {
        int Sum = 0;
        for (int i = 0; i < nums.size(); i++) {// 1 ---> 1,2 -> 1,1
            HashSet<Integer> Store_uniqueElements = new HashSet<>();// each time when i increment new hashset creat ,it avoid duplicate numbers
            for (int j = i; j < nums.size(); j++) {
                // 1->1 = store [1],[1,2],[1,2,1] == [1,2] 1 avoid duplicate but operation same perform and add prev sum 
                // 2->1 = store [2],[2,1]
                // 1  = store [1]
                Store_uniqueElements.add(nums.get(j));
                
                // Get count of unique elements in current subarray nums[i...j]
                int distinctCount = Store_uniqueElements.size();
                
                // squre of eliment  add in sum 
                Sum = Sum + (distinctCount * distinctCount);
            }
        }
        
        return Sum;
    }
}