class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0; // count of flowers we can place
        
        for (int i = 0; i < flowerbed.length; i++) {
            
            // check left and right plots
            if (flowerbed[i] == 0) {
                int left = (i == 0) ? 0 : flowerbed[i - 1];          // left neighbor
                int right = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1]; // right neighbor
                
                // if both sides are empty, we can plant
                if (left == 0 && right == 0) {
                    flowerbed[i] = 1; // plant flower
                    count++;          // increase count
                    
                    if (count >= n) return true; // enough flowers placed
                }
            }
        }
        return count >= n; // final check
    }
}
