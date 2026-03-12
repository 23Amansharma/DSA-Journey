class Solution {
    fun maxStability(n: Int, edges: Array<IntArray>, k: Int): Int {
        var maxPossibleStrength = 0
        var mandatoryCount = 0
        
        // Find exact bounds and array sizes
        for (e in edges) {
            if (e[3] == 1) mandatoryCount++
            val potential = if (e[3] == 1) e[2] else e[2] * 2
            if (potential > maxPossibleStrength) maxPossibleStrength = potential
        }
        
        // Pre-partition to avoid conditional branching in the hot loop
        val mandatory = Array(mandatoryCount) { IntArray(3) }
        val optional = Array(edges.size - mandatoryCount) { IntArray(3) }
        var mIdx = 0
        var oIdx = 0
        
        val initialDsu = DSU(n)
        var minMandatory = Int.MAX_VALUE
        
        for (e in edges) {
            if (e[3] == 1) {
                if (!initialDsu.union(e[0], e[1])) return -1 // Cycle detected
                if (e[2] < minMandatory) minMandatory = e[2]
                mandatory[mIdx][0] = e[0]
                mandatory[mIdx][1] = e[1]
                mandatory[mIdx][2] = e[2]
                mIdx++
            } else {
                optional[oIdx][0] = e[0]
                optional[oIdx][1] = e[1]
                optional[oIdx][2] = e[2]
                oIdx++
            }
        }
        
        // Pre-allocate the worker DSU
        val workerDsu = DSU(n)
        if (!canAchieve(n, mandatory, optional, k, 0, workerDsu)) return -1
        
        var left = 1
        var right = if (minMandatory == Int.MAX_VALUE) maxPossibleStrength else minOf(maxPossibleStrength, minMandatory)
        var maxStability = -1
        
        // Binary Search Hot Loop
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (canAchieve(n, mandatory, optional, k, mid, workerDsu)) {
                maxStability = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        
        return maxStability
    }

    private fun canAchieve(n: Int, mandatory: Array<IntArray>, optional: Array<IntArray>, k: Int, target: Int, dsu: DSU): Boolean {
        dsu.reset() // $O(N)$ reset instead of $O(N)$ allocation
        var components = n

        for (e in mandatory) {
            if (e[2] < target) return false
            if (dsu.union(e[0], e[1])) components--
        }

        for (e in optional) {
            if (e[2] >= target) {
                if (dsu.union(e[0], e[1])) components--
            }
        }

        var upgrades = 0
        for (e in optional) {
            if (e[2] < target && e[2] * 2 >= target) {
                if (dsu.union(e[0], e[1])) {
                    components--
                    upgrades++
                }
            }
        }

        return components == 1 && upgrades <= k
    }

    // Stack-safe, reusable DSU
    class DSU(val n: Int) {
        private val parent = IntArray(n)

        init {
            reset()
        }

        fun reset() {
            for (i in 0 until n) parent[i] = i
        }

        fun find(i: Int): Int {
            var root = i
            while (root != parent[root]) root = parent[root]
            
            // Iterative path compression
            var curr = i
            while (curr != root) {
                val nxt = parent[curr]
                parent[curr] = root
                curr = nxt
            }
            return root
        }

        fun union(i: Int, j: Int): Boolean {
            val rootI = find(i)
            val rootJ = find(j)
            if (rootI != rootJ) {
                parent[rootI] = rootJ
                return true
            }
            return false
        }
    }
}