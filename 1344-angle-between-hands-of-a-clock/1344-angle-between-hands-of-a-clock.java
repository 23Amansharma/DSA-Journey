class Solution {
    public double angleClock(int hour, int minutes) {
        double minuteAngle = minutes * 6.0;
        double hourAngle = (hour % 12 + minutes / 60.0) * 30.0;
        
        double diff = Math.abs(hourAngle - minuteAngle);
        
        return Math.min(diff, 360.0 - diff);
    }
}