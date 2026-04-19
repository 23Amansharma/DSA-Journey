class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int storevalue = 0;
        /*
    for(int i = 0;i<nums1.length;i++){
        for(int j = i;j<nums2.length;j++){
            if(nums1[i]<= nums2[j]){
                storevalue = Math.max(storevalue,j-i);
            }
        }
    }
         return storevalue;
    }
}
*/
int i =0,j = 0;
while(i<nums1.length && j<nums2.length){
    if(nums1[i]<=nums2[j]) {storevalue = Math.max(storevalue,j-i);
    j++;
    }
    else {i++;j++;}
}
return storevalue;
    }
}