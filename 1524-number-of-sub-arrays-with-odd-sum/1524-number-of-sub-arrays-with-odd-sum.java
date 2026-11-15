class Solution {
    public int numOfSubarrays(int[] arr) {
        long count = 0; // Bade answers ke liye long use
        int MOD = 1000000007;
        
        int oddCount = 0;
        int evenCount = 1; // 0 sum ko even maante hain, isliye initial count 1 hai
        int prefixSum = 0;
        
        for (int num : arr) {
            prefixSum += num;
            
            // Agar current prefixSum odd hai
            if (prefixSum % 2 != 0) {
                count = (count + evenCount) % MOD;
                oddCount++;
            } 
            // Agar current prefixSum even hai
            else {
                count = (count + oddCount) % MOD;
                evenCount++;
            }
        }
        
        return (int) count;
    }
}