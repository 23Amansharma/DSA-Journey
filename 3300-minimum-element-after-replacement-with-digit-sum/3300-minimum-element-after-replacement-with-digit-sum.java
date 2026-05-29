class Solution {
    public int minElement(int[] nums) {
         int min = Integer.MAX_VALUE;

        for (int x : nums) {
            String numtostr = Integer.toString(Math.abs(x));
            int store = 0; 

            for (int i = 0; i < numtostr.length(); i++) { 
                char ch = numtostr.charAt(i);
                int digit = Character.getNumericValue(ch); 
                store += digit;
            }

            if (store < min) {
                min = store; 
            }
        }
        return min;
}
}