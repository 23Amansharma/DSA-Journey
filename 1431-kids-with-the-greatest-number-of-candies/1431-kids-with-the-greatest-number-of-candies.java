import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        //Find the current maximum candies anyone has
        int Maxsum = 0;
        for (int i : candies) {
            if (i> Maxsum) {
                Maxsum = i;
            }
        }
        // Check each kid's potential total
        List<Boolean> result = new ArrayList<>();
        for (int i : candies) {
            result.add(i + extraCandies >= Maxsum);
        }
        return result;
    }
}