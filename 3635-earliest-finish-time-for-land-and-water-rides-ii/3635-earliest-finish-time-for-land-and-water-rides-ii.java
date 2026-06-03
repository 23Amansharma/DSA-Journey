import java.util.Arrays;

class Solution {
    
    // Encapsulating the ride data for cleaner object-oriented handling
    private static class Ride {
        int start;
        int duration;
        
        public Ride(int start, int duration) {
            this.start = start;
            this.duration = duration;
        }
    }

    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;

        // Group data into objects
        Ride[] landRides = new Ride[n];
        for (int i = 0; i < n; i++) {
            landRides[i] = new Ride(landStartTime[i], landDuration[i]);
        }
        
        Ride[] waterRides = new Ride[m];
        for (int i = 0; i < m; i++) {
            waterRides[i] = new Ride(waterStartTime[i], waterDuration[i]);
        }

        // Sort both arrays by their starting times in ascending order
        Arrays.sort(landRides, (a, b) -> Integer.compare(a.start, b.start));
        Arrays.sort(waterRides, (a, b) -> Integer.compare(a.start, b.start));

        // Get the absolute minimum from both possible sequential orders
        return Math.min(
            findMinimumFinishTime(landRides, waterRides),
            findMinimumFinishTime(waterRides, landRides)
        );
    }

    private int findMinimumFinishTime(Ride[] firstRides, Ride[] secondRides) {
        int m = secondRides.length;

        // PRECOMPUTATION: Prefix array for the minimum duration seen so far.
        // Useful when the second ride starts BEFORE or EXACTLY WHEN the first ride finishes.
        int[] prefixMinDuration = new int[m];
        prefixMinDuration[0] = secondRides[0].duration;
        for (int i = 1; i < m; i++) {
            prefixMinDuration[i] = Math.min(prefixMinDuration[i - 1], secondRides[i].duration);
        }

        // PRECOMPUTATION: Suffix array for the minimum finish time (start + duration) from current index to the end.
        // Useful when the second ride starts AFTER the first ride finishes.
        int[] suffixMinFinishTime = new int[m];
        suffixMinFinishTime[m - 1] = secondRides[m - 1].start + secondRides[m - 1].duration;
        for (int i = m - 2; i >= 0; i--) {
            suffixMinFinishTime[i] = Math.min(suffixMinFinishTime[i + 1], secondRides[i].start + secondRides[i].duration);
        }

        int absoluteMinFinish = Integer.MAX_VALUE;

        // Iterate through each ride in the first sequence
        for (Ride firstRide : firstRides) {
            int firstFinish = firstRide.start + firstRide.duration;

            // BINARY SEARCH: Find the boundary index in secondRides where start time > firstFinish
            int left = 0, right = m - 1;
            int splitIndex = m; 
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (secondRides[mid].start > firstFinish) {
                    splitIndex = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            int currentMin = Integer.MAX_VALUE;

            // SCENARIO 1: Second ride opens before or exactly when the first ride finishes.
            // We start the second ride immediately at 'firstFinish'.
            if (splitIndex > 0) {
                currentMin = Math.min(currentMin, firstFinish + prefixMinDuration[splitIndex - 1]);
            }

            // SCENARIO 2: Second ride opens after the first ride finishes.
            // We have to wait, so the finish time is strictly dictated by the second ride's (start + duration).
            if (splitIndex < m) {
                currentMin = Math.min(currentMin, suffixMinFinishTime[splitIndex]);
            }

            // Update the overall minimum finish time
            absoluteMinFinish = Math.min(absoluteMinFinish, currentMin);
        }

        return absoluteMinFinish;
    }
}