#include <stdlib.h>

// Helper functions for math operations
static inline int min(int a, int b) { 
    return a < b ? a : b; 
}

static inline int abs_val(int a) { 
    return a < 0 ? -a : a; 
}

// Calculates the shortest circular distance between two indices
static inline int dist(int a, int b, int n) {
    int d = abs_val(a - b);
    return min(d, n - d);
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
// Yahan function ka naam solveQueries kar diya gaya hai
int* solveQueries(int* nums, int numsSize, int* queries, int queriesSize, int* returnSize) {
    int n = numsSize;
    
    // Find the maximum value to allocate exactly enough space for frequency tracking
    int max_val = 0;
    for (int i = 0; i < n; i++) {
        if (nums[i] > max_val) {
            max_val = nums[i];
        }
    }

    // Allocate arrays for tracking first/last occurrences and frequencies
    int* first = (int*)malloc((max_val + 1) * sizeof(int));
    int* last = (int*)malloc((max_val + 1) * sizeof(int));
    int* count = (int*)calloc(max_val + 1, sizeof(int));

    for (int i = 0; i <= max_val; i++) {
        first[i] = -1;
        last[i] = -1;
    }

    // Allocate arrays for doubly linked indexing and precomputed answers
    int* prev = (int*)malloc(n * sizeof(int));
    int* next = (int*)malloc(n * sizeof(int));
    int* ans = (int*)malloc(n * sizeof(int));

    // Pass 1: Build the 'prev' and 'next' relationships for identical elements
    for (int i = 0; i < n; i++) {
        int v = nums[i];
        count[v]++;
        
        if (last[v] != -1) {
            next[last[v]] = i;
            prev[i] = last[v];
        } else {
            first[v] = i;
        }
        last[v] = i;
    }

    // Pass 2: Calculate the minimum circular distance for every element
    for (int i = 0; i < n; i++) {
        int v = nums[i];
        
        // If the element is unique, no equal element exists
        if (count[v] == 1) {
            ans[i] = -1;
        } else {
            int p, nx;
            
            // Handle wrap-around for the first element
            if (i == first[v]) {
                p = last[v];
            } else {
                p = prev[i];
            }
            
            // Handle wrap-around for the last element
            if (i == last[v]) {
                nx = first[v];
            } else {
                nx = next[i];
            }
            
            // The closest match is strictly either the predecessor or successor
            int d1 = dist(i, p, n);
            int d2 = dist(i, nx, n);
            ans[i] = min(d1, d2);
        }
    }

    // Map the precomputed answers to the queries
    int* res = (int*)malloc(queriesSize * sizeof(int));
    for (int i = 0; i < queriesSize; i++) {
        res[i] = ans[queries[i]];
    }

    // Free the dynamically allocated helper memory
    free(first);
    free(last);
    free(count);
    free(prev);
    free(next);
    free(ans);

    *returnSize = queriesSize;
    return res;
}