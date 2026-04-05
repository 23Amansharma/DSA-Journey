class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        
        int n = moves.length();
        for (int i = 0; i < n; i++) {
            char move = moves.charAt(i);
            
            if (move == 'U') {
                y++;
            } else if (move == 'D') {
                y--;
            } else if (move == 'R') {
                x++;
            } else if (move == 'L') {
                x--;
            }
        }
        
        // If both x and y are 0, it returned to the origin
        return x == 0 && y == 0;
    }
}