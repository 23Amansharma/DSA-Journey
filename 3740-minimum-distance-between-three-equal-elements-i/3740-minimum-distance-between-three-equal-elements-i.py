class Solution:
    def minimumDistance(self, nums):
        pos = {}
        
        for i in range(len(nums)):
            if nums[i] not in pos:
                pos[nums[i]] = []
            pos[nums[i]].append(i)
            
        min_dist = float('inf')
        
        for num in pos:
            arr = pos[num]
            if len(arr) >= 3:
                for i in range(len(arr) - 2):
                    j = arr[i+1]
                    k = arr[i+2]
                    i_idx = arr[i]
                    
                    dist = abs(i_idx - j) + abs(j - k) + abs(k - i_idx)
                    
                    if dist < min_dist:
                        min_dist = dist
                        
        if min_dist == float('inf'):
            return -1
            
        return min_dist