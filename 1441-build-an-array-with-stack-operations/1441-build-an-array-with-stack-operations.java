class Solution {
    public List<String> buildArray(int[] target, int n) {
     List<String> stack = new ArrayList<>(); 
     int value  = 0;
     for(int i = 1;i<= n; i++){
        if(value == target.length) break; // rules if not empty then perform push/pop
        if(i == target[value]){
            stack.add("Push");
            value++;
        }
        else{
            stack.add("Push");
            stack.add("Pop");
        }
     }
 return stack;   
}
}