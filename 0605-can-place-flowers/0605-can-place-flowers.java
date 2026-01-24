class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;

        for (int i = 0; i < len; i++) {

            // current plot empty hona chahiye
            if (flowerbed[i] == 0) {

                // left & right check (boundary safe)
                boolean leftEmpty  = (i == 0) || flowerbed[i - 1] == 0;
                boolean rightEmpty = (i == len - 1) || flowerbed[i + 1] == 0;

                // dono side empty → flower place
                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1; // plant
                    n--;              // one flower placed

                    if (n == 0) return true; // early exit
                }
            }
        }
        return n <= 0;
    }
}
