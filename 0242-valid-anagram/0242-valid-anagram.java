class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
       HashMap<Character, Integer> mp1 = method(s);
       HashMap<Character,Integer> mp2 = method(t);
       return mp1.equals(mp2); //true
    }
     static HashMap<Character, Integer> method(String s){
        HashMap<Character,Integer> mapname = new HashMap<>();
        for(int i  = 0;i<s.length();i++){
            char ch = s.charAt(i);
            if(!mapname.containsKey(ch)){
                mapname.put(ch,1);
            }else{
                int currentFrequency = mapname.get(ch);
                mapname.put(ch,currentFrequency + 1);
            }
        }
        return mapname;
     }
}