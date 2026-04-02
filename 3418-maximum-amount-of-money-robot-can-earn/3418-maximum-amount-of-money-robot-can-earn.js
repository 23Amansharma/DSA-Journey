/**
 * @param {number[][]} coins
 * @return {number}
 */
var maximumAmount = function(coins) {
    const m = coins.length;
    const n = coins[0].length;
    
    // Space Optimization: Maintain only the previous row and the current row.
    // Each cell stores an array of size 3: [max_with_0_neutralizations, max_with_1, max_with_2]
    const prevRow = Array.from({ length: n }, () => [-Infinity, -Infinity, -Infinity]);
    const currRow = Array.from({ length: n }, () => [-Infinity, -Infinity, -Infinity]);
    
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const val = coins[i][j];
            
            // Base Case: Starting cell (0, 0)
            if (i === 0 && j === 0) {
                currRow[j][0] = val;
                currRow[j][1] = val < 0 ? 0 : -Infinity; 
                currRow[j][2] = -Infinity; // Impossible to use 2 neutralizations on cell 1
                continue;
            }
            
            // Evaluate for k = 0, 1, 2 neutralizations used so far
            for (let k = 0; k <= 2; k++) {
                // 1. Find the best path arriving at this cell with exactly 'k' neutralizations
                const top = i > 0 ? prevRow[j][k] : -Infinity;
                const left = j > 0 ? currRow[j - 1][k] : -Infinity;
                const bestPrev = Math.max(top, left);
                
                // Standard Move: Take the cell value (whether positive or negative)
                let currentVal = bestPrev !== -Infinity ? bestPrev + val : -Infinity;
                let neutralizeVal = -Infinity;
                
                // 2. Neutralize Move: If it's a robber (val < 0) and we have neutralizations (k > 0)
                if (val < 0 && k > 0) {
                    const topPrevK = i > 0 ? prevRow[j][k - 1] : -Infinity;
                    const leftPrevK = j > 0 ? currRow[j - 1][k - 1] : -Infinity;
                    const bestPrevK = Math.max(topPrevK, leftPrevK);
                    
                    if (bestPrevK !== -Infinity) {
                        neutralizeVal = bestPrevK; // Add 0 instead of subtracting val
                    }
                }
                
                // Store the maximum outcome for this specific 'k' state
                currRow[j][k] = Math.max(currentVal, neutralizeVal);
            }
        }
        
        // Transfer current row state to previous row state for the next row iteration
        for (let j = 0; j < n; j++) {
            prevRow[j][0] = currRow[j][0];
            prevRow[j][1] = currRow[j][1];
            prevRow[j][2] = currRow[j][2];
        }
    }
    
    // The answer is the maximum possible value at the bottom-right corner across all 'k' states
    return Math.max(currRow[n - 1][0], currRow[n - 1][1], currRow[n - 1][2]);
};