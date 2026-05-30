class Solution { 
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        /*
        for(int i = 0;i<strs[0].length();i++){
            char ch = strs[0].charAt(i);
            for(int j = 1;j<strs.length;j++)
                if(  i == strs[j].length() || strs[j].charAt(i) !=  ch) 
                return strs[0].substring(0, i);
            }
            return strs[0];*/
        Arrays.sort(strs);int i = 0;
        String first = strs[0],last = strs[strs.length - 1];
        // check characters match 
        while (i < first.length() && i < last.length()) {
            if (first.charAt(i) == last.charAt(i))  i++; 
            else  break;
            
        }  
        // common string return 
        return first.substring(0, i);
    }
}