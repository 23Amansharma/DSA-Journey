import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        // Kyo: 'n' me array ki size store ki taaki baar baar nums.length na likhna pade.
        int n = nums.length;
        
        // Kaise & Kyo: Naya array banaya jisme final answer store hoga. 
        // Type 'long' rakha hai kyo ki LeetCode par sum bohot bada ho sakta hai aur int overflow kar jayega.
        long store[] = new long[n]; 
        
        // Kyo: Nested loop (O(N^2)) se bachne ke liye Hashmap banaya.
        // Kis liye: Ye array ke har number ko ek group me daal dega, aur unke saare index ek List me ikattha kar lega.
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Kadam 1: Poore array me ghoom kar map ko bharna
        for(int i = 0; i < n; i++) {
            
            // Kaise: Agar number map me pehle se nahi hai, to uske liye ek khali list banao.
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            
            // Kis liye: Map se us number ki list nikalo aur current index (i) usme save kar do.
            map.get(nums[i]).add(i);
        }
        
        // Kadam 2: Map me rakhe har number ke index groups par math formula lagana
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            
            // Kaise: Current number ke sabhi index ki ek list bahar nikaali
            List<Integer> indices = entry.getValue(); 
            int size = indices.size();
            
            // Kyo: Right side ka difference ek jhatke me nikalne ke liye hume pehle hi sabhi index ka Total Sum chahiye hoga.
            long totalSum = 0;
            for(int index : indices) {
                totalSum += index;
            }
            
            // Kis liye: Ye track karega ki jahan hum khade hain, uske piche (left me) index ka total kitna hai. Shuru me ye 0 hoga.
            long leftSum = 0; 
            
            // Kaise: Ab array me nahi, sirf us number ke ikattha kiye hue index list par loop chalayenge.
            for(int i = 0; i < size; i++) {
                int currentIndex = indices.get(i);
                
                // Kis liye (Right Sum nikalna): Total me se peeche wala hissa (leftSum) aur khud ka hissa (currentIndex) ghata diya, to aage (right) ka sum mil gaya.
                long rightSum = totalSum - leftSum - currentIndex;
                
                // Kyo: Formula lagane ke liye pata hona chahiye ki left aur right me kitne numbers (ginti) mojud hain.
                long leftCount = i; 
                long rightCount = size - 1 - i;
                
                // Kaise (Left Diff Math): Left side ke numbers hamesha current se chhote honge. 
                // Isliye formula: (Kitne numbers hain * Current Index) - (Peeche ke numbers ka total sum)
                long leftDiff = (leftCount * currentIndex) - leftSum;
                
                // Kaise (Right Diff Math): Right side ke numbers hamesha current se bade honge.
                // Isliye formula: (Aage ke numbers ka total sum) - (Kitne numbers hain * Current Index)
                long rightDiff = rightSum - (rightCount * currentIndex);
                
                // Kis liye: Dono taraf ka absolute difference mil gaya. Unhe jod kar seedha 'store' array me uski sahi jagah par daal diya.
                store[currentIndex] = leftDiff + rightDiff;
                
                // Kis liye: Agle step (index) par jane se pehle, current index ko leftSum me daal diya taaki agle loop ke liye data update rahe.
                leftSum += currentIndex;
            }
        }
        
        // Kis liye: Jab saare groups process ho gaye, toh answer wala array wapas bhej diya.
        return store;
    }
}