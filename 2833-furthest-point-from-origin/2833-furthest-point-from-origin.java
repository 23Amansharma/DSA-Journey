class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int Lsum = 0;
        int Rsum = 0;
        int us = 0;
    for(int i = 0;i<moves.length();i++)
    {
        if(moves.charAt(i) == 'L') Lsum++;
       else if(moves.charAt(i) == 'R') Rsum++;
        else us++;
    }
    return Math.abs(Lsum-Rsum)+us;
    }
}