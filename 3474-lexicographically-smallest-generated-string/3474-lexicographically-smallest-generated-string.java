class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] word = new char[n + m - 1];
        java.util.Arrays.fill(word, '?');
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int k = 0; k < m; k++) {
                    if (word[i + k] != '?' && word[i + k] != str2.charAt(k)) {
                        return ""; 
                    }
                    word[i + k] = str2.charAt(k);
                }
            }
        }
        
        int[] qCount = new int[n];
        int[] misCount = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                for (int k = 0; k < m; k++) {
                    if (word[i + k] == '?') {
                        qCount[i]++;
                    } else if (word[i + k] != str2.charAt(k)) {
                        misCount[i]++;
                    }
                }
                if (qCount[i] == 0 && misCount[i] == 0) {
                    return ""; 
                }
            }
        }
        
        for (int j = 0; j < word.length; j++) {
            if (word[j] == '?') {
                boolean[] forbidden = new boolean[26];
                
                int start = Math.max(0, j - m + 1);
                int end = Math.min(n - 1, j);
                
                for (int i = start; i <= end; i++) {
                    if (str1.charAt(i) == 'F' && qCount[i] == 1 && misCount[i] == 0) {
                        forbidden[str2.charAt(j - i) - 'a'] = true;
                    }
                }
                
                char selected = '?';
                for (char c = 'a'; c <= 'z'; c++) {
                    if (!forbidden[c - 'a']) {
                        selected = c;
                        break;
                    }
                }
                
                if (selected == '?') return ""; 
                word[j] = selected;
                
                for (int i = start; i <= end; i++) {
                    if (str1.charAt(i) == 'F') {
                        qCount[i]--;
                        if (selected != str2.charAt(j - i)) {
                            misCount[i]++;
                        }
                    }
                }
            }
        }
        
        return new String(word);
    }
}