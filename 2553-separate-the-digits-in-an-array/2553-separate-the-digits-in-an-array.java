import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        
        // Iterate through each number in the array
        for (int num : nums) {
            // Convert each number to string and then to character digits
            String s = Integer.toString(num);
            for (char c : s.toCharArray()) {
                // Convert character back to integer
                list.add(c - '0');
            }
        }
        
        // Convert List<Integer> to int[]
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
