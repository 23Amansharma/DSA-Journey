import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] A = new long[n * 2];
        
        for (int i = 0; i < n; i++) {
            long x = points[i][0];
            long y = points[i][1];
            long pos;
            
            if (y == 0) {
                pos = x;
            } else if (x == side) {
                pos = side + y;
            } else if (y == side) {
                pos = 3L * side - x;
            } else {
                pos = 4L * side - y;
            }
            A[i] = pos;
        }
        
        Arrays.sort(A, 0, n);
        
        for (int i = 0; i < n; i++) {
            A[i + n] = A[i] + 4L * side;
        }

        int low = 1;
        int high = side;
        int ans = 1;
        int[] nxt = new int[n * 2];

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid, A, n, k, 4L * side, nxt)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }

    private boolean check(int x, long[] A, int n, int k, long perimeter, int[] nxt) {
        int j = 0;
        for (int i = 0; i < n * 2; i++) {
            while (j < n * 2 && A[j] - A[i] < x) {
                j++;
            }
            nxt[i] = j;
        }

        for (int i = 0; i < n; i++) {
            int count = 1;
            int curr = i;
            long limit = A[i] + perimeter - x;
            
            while (count < k) {
                curr = nxt[curr];
                if (curr >= n * 2 || A[curr] > limit) {
                    break;
                }
                count++;
            }
            
            if (count == k) {
                return true;
            }
        }
        
        return false;
    }
}