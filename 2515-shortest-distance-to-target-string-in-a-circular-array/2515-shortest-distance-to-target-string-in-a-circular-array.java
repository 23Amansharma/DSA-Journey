class Solution {
    /**
     * Finds the shortest distance to a target string in a circular array.
     * * @param words      Array of strings.
     * @param target     The string to search for.
     * @param startIndex The index to start searching from.
     * @return           The shortest distance to the target, or -1 if not found.
     */
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = -1;

        for (int i = 0; i < n; i++) {
            // Use .equals() for string content comparison in Java
            if (words[i].equals(target)) {
                
                // Calculate absolute difference between current index and startIndex
                int absDiff = Math.abs(startIndex - i);
                
                // Calculate the shortest path (direct vs. wrap-around)
                int currentDistance = Math.min(absDiff, n - absDiff);

                // Update minDistance if it's the first match or a shorter path is found
                if (minDistance == -1 || currentDistance < minDistance) {
                    minDistance = currentDistance;
                }
            }
        }

        return minDistance;
    }
}