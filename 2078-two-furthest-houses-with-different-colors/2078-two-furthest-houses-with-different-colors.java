class Solution {
    public int maxDistance(int[] colors) {
        int maxdist = 0;
    for(int i = 0;i<colors.length;i++){
        for(int j = colors.length-1;j>i;j--){
            if(colors[i] != colors[j]){
                maxdist = Math.max(maxdist,j-i);
                break;
            }
        }
    }
    return maxdist;
    }
}