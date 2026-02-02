import java.util.*;

class Solution {
    // Smallest elements ko track karne ke liye 'left' map aur baki ke liye 'right' map
    private TreeMap<Integer, Integer> left = new TreeMap<>();
    private TreeMap<Integer, Integer> right = new TreeMap<>();
    private int leftCount = 0;
    private long leftSum = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        // Humein nums[0] ke alawa k-1 aur elements chahiye
        int m = k - 1; 
        // Pehla element fixed hai, toh humein window mein se m-1 elements aur uthane hain
        int target = m - 1; 

        // Initial window set up: elements from index 2 to 1 + dist
        // Kyunki i1 (second subarray start) index 1 par fixed maan rahe hain pehle step ke liye
        for (int i = 2; i <= Math.min(1 + dist, n - 1); i++) {
            add(nums[i]);
        }
        balance(target);

        // Initial cost: nums[0] + nums[1] + (smallest target elements in window)
        long minTotal = (long) nums[0] + nums[1] + leftSum;

        // Sliding Window: i1 ko shift karenge index 2 se n-(k-1) tak
        for (int i = 2; i <= n - m; i++) {
            // Purana fixed element (nums[i-1]) window se bahar jayega
            // Aur naya fixed element (nums[i]) window se hatana padega kyunki wo 'i1' ban raha hai
            remove(nums[i]);
            // Window ka right boundary shift karenge: i + dist tak
            int nextIdx = i + dist;
            if (nextIdx < n) {
                add(nums[nextIdx]);
            }// 'left' map mein hamesha smallest 'target' elements maintain karenge
            balance(target); // Current cost calculate karke minimum update karenge
            long currentCost = (long) nums[0] + nums[i] + leftSum;
            minTotal = Math.min(minTotal, currentCost);
        }
        return minTotal;
    }// Naya number window mein add karne ke liye
    private void add(int val) {
        if (!left.isEmpty() && val <= left.lastKey()) {
            left.put(val, left.getOrDefault(val, 0) + 1);
            leftSum += val;
            leftCount++;
        } else {
            right.put(val, right.getOrDefault(val, 0) + 1);
        }
    }
    // Number window se remove karne ke liye
    private void remove(int val) {
        if (left.containsKey(val)) {
            left.put(val, left.get(val) - 1);
            if (left.get(val) == 0) left.remove(val);
            leftSum -= val;
            leftCount--;
        } else if (right.containsKey(val)) {
            right.put(val, right.get(val) - 1);
            if (right.get(val) == 0) right.remove(val);
        }
    }
    // Dono maps ko balance karne ke liye taaki left mein hamesha smallest elements rahein
    private void balance(int target) {
        // Agar left mein elements kam hain, toh right se uthao
        while (leftCount < target && !right.isEmpty()) {
            int first = right.firstKey();
            moveRightToLeft(first);
        }
        // Agar left mein zyada hain, toh bade wale elements right mein shift karo
        while (leftCount > target) {
            int last = left.lastKey();
            moveLeftToRight(last);
        }
    }
    private void moveRightToLeft(int val) {
        right.put(val, right.get(val) - 1);
        if (right.get(val) == 0) right.remove(val);
        left.put(val, left.getOrDefault(val, 0) + 1);
        leftSum += val;
        leftCount++;
    }
    private void moveLeftToRight(int val) {
        left.put(val, left.get(val) - 1);
        if (left.get(val) == 0) left.remove(val);
        leftSum -= val;
        leftCount--;
        right.put(val, right.getOrDefault(val, 0) + 1);
    }
}