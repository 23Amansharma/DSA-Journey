/**
 * @param {number[]} nums
 * @return {number}
 */
var minimumDistance = function(nums) {
    let n = nums.length;
    
    // Using Int32Array for maximum performance and minimum memory footprint
    // Size is n + 1 because constraints say 1 <= nums[i] <= n
    let last1 = new Int32Array(n + 1).fill(-1);
    let last2 = new Int32Array(n + 1).fill(-1);
    let last3 = new Int32Array(n + 1).fill(-1);

    let minDist = Infinity;

    for (let i = 0; i < n; i++) {
        let num = nums[i];
        
        // Shift the memory: 2nd becomes 3rd, 1st becomes 2nd
        last3[num] = last2[num];
        last2[num] = last1[num];
        
        // Update the most recent position
        last1[num] = i;

        // If this number has appeared at least 3 times
        if (last3[num] !== -1) {
            // Distance is always 2 * (max_idx - min_idx)
            let dist = 2 * (last1[num] - last3[num]);
            if (dist < minDist) {
                minDist = dist;
            }
        }
    }

    // If minDist was never updated, no good tuple exists
    return minDist === Infinity ? -1 : minDist;
};