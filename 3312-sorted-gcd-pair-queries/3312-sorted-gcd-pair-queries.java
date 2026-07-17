import java.util.Arrays;

public class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }

        long[] countMultiples = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                countMultiples[g] += freq[multiple];
            }
        }

        long[] exactGcdCount = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long totalPairsWithDivisorG = (countMultiples[g] * (countMultiples[g] - 1)) / 2;
            long duplicatePairs = 0;
            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                duplicatePairs += exactGcdCount[multiple];
            }
            exactGcdCount[g] = totalPairsWithDivisorG - duplicatePairs;
        }

        long[] prefixSums = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            prefixSums[g] = prefixSums[g - 1] + exactGcdCount[g];
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long targetIdx = queries[i];
            
            int low = 1, high = maxVal;
            int resultG = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > targetIdx) {
                    resultG = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = resultG;
        }

        return answer;
    }
}
