class Solution {
    public List<String> buildArray(int[] target, int n) {
     List<String> newstack = new ArrayList<>(); 
     int value  = 0;
     for(int i = 1;i<= n; i++){
        if(value == target.length) break; // rules if not empty then perform push/pop
        if(i == target[value]){
            newstack.add("Push");
            value++;
        }
        else{ 
            newstack.add("Push");// if target  value not match with stack index
            newstack.add("Pop");
        }
     }
 return newstack;   
}
}