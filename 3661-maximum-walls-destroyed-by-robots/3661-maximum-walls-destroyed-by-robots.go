import (
	"sort"
)

type Robot struct {
	pos  int
	dist int
}

// Function name strictly updated to maxWalls
func maxWalls(robots []int, distance []int, walls []int) int {
	// Define min and max locally to avoid any Go version conflicts
	minVal := func(a, b int) int {
		if a < b {
			return a
		}
		return b
	}
	maxVal := func(a, b int) int {
		if a > b {
			return a
		}
		return b
	}

	n := len(robots)
	robs := make([]Robot, n)
	for i := 0; i < n; i++ {
		robs[i] = Robot{pos: robots[i], dist: distance[i]}
	}

	// Sort robots by their positions
	sort.Slice(robs, func(i, j int) bool {
		return robs[i].pos < robs[j].pos
	})

	// Sort walls
	sort.Ints(walls)

	baseWalls := 0
	var W []int

	// Separate walls exactly on robots from walls in open spaces
	i, j := 0, 0
	for i < len(walls) && j < n {
		if walls[i] == robs[j].pos {
			baseWalls++
			i++
			j++
		} else if walls[i] < robs[j].pos {
			W = append(W, walls[i])
			i++
		} else {
			j++
		}
	}
	for ; i < len(walls); i++ {
		W = append(W, walls[i])
	}

	// Helper to count walls within a range [A, B]
	count := func(A, B int) int {
		if A > B {
			return 0
		}
		idx1 := sort.Search(len(W), func(k int) bool { return W[k] >= A })
		idx2 := sort.Search(len(W), func(k int) bool { return W[k] > B })
		return idx2 - idx1
	}

	dp := make([][2]int, n)

	// Base case for the first robot
	dp[0][0] = count(robs[0].pos-robs[0].dist, robs[0].pos-1)
	dp[0][1] = 0

	// DP Transitions
	for k := 1; k < n; k++ {
		prevL := dp[k-1][0]
		prevR := dp[k-1][1]

		posPrev := robs[k-1].pos
		distPrev := robs[k-1].dist
		posCurr := robs[k].pos
		distCurr := robs[k].dist

		countPrevR := count(posPrev+1, minVal(posPrev+distPrev, posCurr-1))
		countCurrL := count(maxVal(posCurr-distCurr, posPrev+1), posCurr-1)

		start1 := posPrev + 1
		end1 := minVal(posPrev+distPrev, posCurr-1)
		start2 := maxVal(posCurr-distCurr, posPrev+1)
		end2 := posCurr - 1

		cnt := count(start1, end1) + count(start2, end2)
		startInt := maxVal(start1, start2)
		endInt := minVal(end1, end2)

		if startInt <= endInt {
			cnt -= count(startInt, endInt)
		}

		dp[k][0] = maxVal(prevL+countCurrL, prevR+cnt)
		dp[k][1] = maxVal(prevL, prevR+countPrevR)
	}

	ans := maxVal(dp[n-1][0], dp[n-1][1]+count(robs[n-1].pos+1, robs[n-1].pos+robs[n-1].dist))

	return ans + baseWalls
}