class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids){
        Arrays.sort(asteroids);
        long planet = mass;
        for(int i = 0;i<asteroids.length;i++){
            if(planet < asteroids[i]) return false;
            planet = planet + asteroids[i];
        }
            return true;
}
}