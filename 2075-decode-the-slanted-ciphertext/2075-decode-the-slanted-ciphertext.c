#include <string.h>
#include <stdlib.h>

char* decodeCiphertext(char* encodedText, int rows) {
    int L = strlen(encodedText);
    if (L == 0 || rows == 1) {
        // For rows == 1, no spaces are padded at the end
        return encodedText;
    }

    int cols = L / rows;
    
    // Allocate memory for the decoded string (+1 for the null terminator)
    char* originalText = (char*)malloc((L + 1) * sizeof(char));
    int idx = 0;

    // Traverse diagonally starting from each column in the first row
    for (int start_col = 0; start_col < cols; start_col++) {
        for (int r = 0; r < rows; r++) {
            int c = start_col + r;
            
            // If the diagonal goes out of the right bound of the matrix, stop
            if (c >= cols) {
                break;
            }
            
            // Map the 2D (r, c) coordinate to the 1D string index
            int string_index = (r * cols) + c;
            originalText[idx++] = encodedText[string_index];
        }
    }

    // Trim trailing spaces from the end of the decoded string
    while (idx > 0 && originalText[idx - 1] == ' ') {
        idx--;
    }
    
    // Null-terminate the finalized string
    originalText[idx] = '\0';

    return originalText;
}