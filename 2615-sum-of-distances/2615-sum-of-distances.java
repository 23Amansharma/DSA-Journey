import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] store = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> indices : map.values()) {
            int m = indices.size();
            long[] prefix = new long[m + 1];

            for (int i = 0; i < m; i++) {
                prefix[i + 1] = prefix[i] + indices.get(i);
            }

            for (int i = 0; i < m; i++) {
                long currIndex = indices.get(i);
                
                long leftCount = i;
                long leftSum = prefix[i];
                long leftTotal = (leftCount * currIndex) - leftSum;

                long rightCount = m - 1 - i;
                long rightSum = prefix[m] - prefix[i + 1];
                long rightTotal = rightSum - (rightCount * currIndex);

                store[(int) currIndex] = leftTotal + rightTotal;
            }
        }

        return store;
    }
}