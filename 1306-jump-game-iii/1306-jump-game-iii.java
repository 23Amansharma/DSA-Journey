import java.util.*;

class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // Found zero
            if (arr[curr] == 0) {
                return true;
            }

            int forward = curr + arr[curr];
            int backward = curr - arr[curr];

            // Move forward
            if (forward < n && !visited[forward]) {
                visited[forward] = true;
                queue.offer(forward);
            }

            // Move backward
            if (backward >= 0 && !visited[backward]) {
                visited[backward] = true;
                queue.offer(backward);
            }
        }

        return false;
    }
}