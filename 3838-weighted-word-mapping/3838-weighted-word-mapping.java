class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder output = new StringBuilder();
        for (String x : words) {
            int currW = 0;
            for (char c : x.toCharArray()) {
                currW += weights[c - 'a'];
            }
            char mappedChar = (char) ('z' - (currW % 26));
            output.append(mappedChar);
        }
        
        return output.toString();
    }
}